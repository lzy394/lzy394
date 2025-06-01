import numpy as np
import cv2

def RGB2YUV_enhance(img, lightness_en=3.5):
    temp_YUV = np.zeros((img.shape[0], img.shape[1], 3), np.uint8)
    res_RGB  = np.zeros((img.shape[0], img.shape[1], 3), np.uint8)
    timg = img.astype(np.float32)
    for i in range(timg.shape[0]):
        for j in range(timg.shape[1]):

            B = timg[i][j][0]
            G = timg[i][j][1]
            R = timg[i][j][2]

            Y = 0.299 * R + 0.587 * G + 0.114 * B
            U = 0.492 * (B - Y) + 128  # Cb分量
            V = 0.877 * (R - Y) + 128  # Cr分量

            Y = np.clip(Y, 0, 255)
            U = np.clip(U, 0, 255)
            V = np.clip(V, 0, 255)

            temp_YUV[i][j][0] = Y.astype(np.uint8)
            temp_YUV[i][j][1] = U.astype(np.uint8)
            temp_YUV[i][j][2] = V.astype(np.uint8)

            Y_en = Y * lightness_en
            Y_en = np.clip(Y_en, 0, 255)

            U_prime = (U - 128) / 0.492
            V_prime = (V - 128) / 0.877
            R_en = Y_en + V_prime
            B_en = Y_en + U_prime
            G_en = (Y_en - 0.299 * R_en - 0.114 * B_en) / 0.587
            R_en = np.clip(R_en, 0, 255)
            G_en = np.clip(G_en, 0, 255)
            B_en = np.clip(B_en, 0, 255)
            res_RGB[i][j][0] = B_en.astype(np.uint8)
            res_RGB[i][j][1] = G_en.astype(np.uint8)
            res_RGB[i][j][2] = R_en.astype(np.uint8)
    return temp_YUV, res_RGB

if __name__ == '__main__':
    img = cv2.imread("test/Lena.jpg")
    imgyuv, res_rgb = RGB2YUV_enhance(img)
    cv2.imshow('orginal image', img)
    cv2.imshow('Y', imgyuv[:,:,0])
    cv2.imshow('U', imgyuv[:,:,1])
    cv2.imshow('V', imgyuv[:,:,2])
    cv2.imshow('Enhance Light', res_rgb)
    cv2.imwrite("after_rgb.jpg", res_rgb)
    cv2.imwrite("rgb2yuv.jpg", imgyuv)
    cv2.waitKey(0)
    cv2.destroyAllWindows()
