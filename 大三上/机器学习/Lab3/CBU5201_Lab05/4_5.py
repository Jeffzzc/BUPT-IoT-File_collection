import numpy as np

# 加载数据
x1_train = np.load("quiz5_x1_train_B.npy")
x2_train = np.load("quiz5_x2_train_B.npy")
x3_train = np.load("quiz5_x3_train_B.npy")
y_train = np.load("quiz5_y_train_B.npy")

# 定义线性分类器的系数
w0 = 0
w1 = 1
w2 = 1
w3 = 1

# 创建设计矩阵，包括偏置项
X_train = np.column_stack((np.ones(x1_train.shape[0]), x1_train, x2_train, x3_train))

# 预测标签
y_pred = np.where(np.dot(X_train, [w0, w1, w2, w3]) >= 0, 1, 0)

# 计算准确性
accuracy = np.mean(y_pred == y_train)

# 四舍五入准确性到两位小数
accuracy_rounded = round(accuracy, 2)

print(f"问题4的答案是：准确性为 {accuracy_rounded}")

# 计算负对数似然
def sigmoid(z):
    return 1 / (1 + np.exp(-z))

def neg_log_likelihood(X, y, w):
    z = np.dot(X, w)
    p = sigmoid(z)
    return -np.mean(y * np.log(p) + (1 - y) * np.log(1 - p))

neg_log_likelihood_value = neg_log_likelihood(X_train, y_train, [w0, w1, w2, w3])

# 四舍五入负对数似然到两位小数
neg_log_likelihood_rounded = round(neg_log_likelihood_value, 2)

print(f"问题5的答案是：负对数似然为 {neg_log_likelihood_rounded}")