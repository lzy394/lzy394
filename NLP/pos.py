import jieba
import jieba.posseg as pseg
from graphviz import Digraph


# 所有学生都必须遵守课堂纪律
def sentence_parser(sentence):
    # 使用jieba库对句子分词处理
    words = pseg.lcut(sentence)
    print("分词结果如下：")
    for word in words:
        print(word.word + "(" + word.flag + ")")
    stack = []  # 栈
    queue = list(words)  # 队列
    dependence_relationship = []  # 保存依存关系
    step = 0
    rules = {  # 定义规则
        'q': ['n'],  # 量词
        'v': ['n', 'q', 'd', 'v'],  # 动词
        'n': ['v'],  # 名词
        'd': ['d', 'vd'],  # 副词
        'vd': ['v']  # 能愿动词
    }

    def print_step(action):  # 每步过程栈、队列和依存关系的变化
        nonlocal step
        step += 1
        print(f"\nStep {step}: {action}")
        print(f"Stack: {[(w.word, w.flag) for w in stack]}")
        print(f"Queue: {[(w.word, w.flag) for w in queue]}")
        print(f"Dependence Relationship: {[(h.word, h.flag, '←', d.word, d.flag) for (h, d) in dependence_relationship]}")

    while queue or len(stack) > 1:
        if len(stack) < 2 and queue:
            stack.append(queue.pop(0))
            print_step("SH(移进)")
            continue

        top = stack[-1]
        second = stack[-2]

        established = False  # 本次是否建立依存关系
        if second.flag == 'v' and top.flag == 'n':
            dependence_relationship.append((top, second))
            stack.pop(-1)
            print_step(f"RR(右弧合并)\n建立依存: {second.word}({second.flag})  → {top.word}({top.flag})")
            continue
        if top.flag in rules[second.flag]:
            dependence_relationship.append((second, top))
            stack.pop(-2)
            print_step(f"RL(左弧合并)\n建立依存: {second.word}({second.flag}) ← {top.word}({top.flag})")
            established = True
        elif second.flag in rules[top.flag]:
            dependence_relationship.append((top, second))
            stack.pop(-1)
            print_step(f"RR(右弧合并)\n建立依存: {second.word}({second.flag}) → {top.word}({top.flag})")
            established = True

        if not established:
            if queue:
                stack.append(queue.pop(0))
                print_step("SH(移进)")
            else:
                if len(stack) >= 2:
                    dependence_relationship.append((stack[-2], stack[-1]))
                    stack.pop(-1)
                    print_step("无对应规则,强制建立依存关系")

    return dependence_relationship


def show_tree(relations):
    dot = Digraph(comment = 'Dependency Tree',
                  format = 'png',
                  graph_attr = {'rankdir': 'TB', 'nodesep': '0.8'},
                  node_attr = {'style': 'filled',
                               'fillcolor': 'lightyellow',
                               'fontname': 'SimHei',
                               'fontsize': '12',
                               'shape': 'box'},
                  edge_attr = {'arrowsize': '0.8',
                               'arrowhead': 'vee',
                               'color': '#606060'})

    node_map = {}
    node_id = 0
    all_labels = set()

    for head, dep in relations:
        all_labels.add(f"{head.word}({head.flag})")
        all_labels.add(f"{dep.word}({dep.flag})")

    for label in sorted(all_labels):
        node_map[label] = f"node{node_id}"
        dot.node(name = f"node{node_id}", label = label)
        node_id += 1

    for head, dep in relations:
        head_label = f"{head.word}({head.flag})"
        dep_label = f"{dep.word}({dep.flag})"
        dot.edge(node_map[dep_label], node_map[head_label])

    roots = [node_map[label] for label in all_labels
             if not any(dep_label == label for (_, dep) in relations)]

    with dot.subgraph() as s:
        s.attr(rank = 'same')
        for root_id in roots:
            s.node(root_id)

    dot.render('dependency_tree', view = True, cleanup = True)


jieba.setLogLevel(jieba.logging.INFO)
jieba.add_word("所有", tag = 'q')
jieba.add_word("必须", tag = 'vd')

# 句子
sentence = "所有学生都必须遵守课堂纪律"
words = []
dependence_relationship = sentence_parser(sentence)

print("\n依存关系:")
for head, dep in dependence_relationship:
    print(f"{head.word}({head.flag}) ← {dep.word}({dep.flag})")

show_tree(dependence_relationship)
