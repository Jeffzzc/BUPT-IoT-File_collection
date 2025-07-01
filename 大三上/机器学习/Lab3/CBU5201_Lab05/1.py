import numpy as np

# 加载数据
x1_train = np.load("quiz5_x1_train_A.npy")
x2_train = np.load("quiz5_x2_train_A.npy")
y_train = np.load("quiz5_y_train_A.npy")

# 定义线性分类器的边界
w0 = 0
w1 = 1
w2 = 2

# 创建设计矩阵，包括偏置项
X_train = np.column_stack((np.ones(x1_train.shape[0]), x1_train, x2_train))

# 预测标签
y_pred = np.where(np.dot(X_train, [w0, w1, w2]) >= 0, 1, 0)

# 计算准确性
accuracy = np.mean(y_pred == y_train)

# 四舍五入到两位小数
accuracy_rounded = round(accuracy, 2)

print(f"The accuracy of the classifier is: {accuracy_rounded}")