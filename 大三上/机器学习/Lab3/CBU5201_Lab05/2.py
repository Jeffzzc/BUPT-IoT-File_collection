import numpy as np

# 加载数据
x1_train = np.load("quiz5_x1_train_A.npy")
x2_train = np.load("quiz5_x2_train_A.npy")
y_train = np.load("quiz5_y_train_A.npy")

# 问题2：定义第二个分类器的系数
w0_2 = 0
w1_2 = 1
w2_2 = -1

# 创建设计矩阵，包括偏置项
X_train = np.column_stack((np.ones(x1_train.shape[0]), x1_train, x2_train))

# 预测标签
y_pred_2 = np.where(np.dot(X_train, [w0_2, w1_2, w2_2]) >= 0, 1, 0)

# 计算错误分类的样本数量
misclassifications_2 = np.sum(y_pred_2 != y_train)

print(misclassifications_2)