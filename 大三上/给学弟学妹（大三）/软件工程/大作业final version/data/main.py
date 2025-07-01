import random
import string
import csv


def generate_password():
    password = ''
    chars = string.ascii_letters + string.digits
    for i in range(8):
        c = random.choice(chars)
        s = str(c)
        password += s
    return password

def generate_name():
    name = ''
    for i in range(6):
        c = random.choice(string.ascii_letters)
        s = str(c)
        name += s
    return name

if __name__ == '__main__':
    major={0:"Telecommunications Engineering and Management",
           1:"Telecommunications Engineering and Management",
           2:"Internet of Things",
           3:"E-commerce"}
    semester = 2
    count = 0
    header = ['name', 'id', 'password', 'year', 'semester', 'major']
    with open('data.csv', 'w', encoding='UTF8', newline='') as f:
        writer = csv.writer(f)
        writer.writerow(header)
        i = 0
        for year in range(1, 5):
            for id in range(0, 641):
                student_id = 2020000000 + (year * 1000000) + 213000 + id
                name = generate_name()
                password = generate_password()
                print("studend_name:", name)
                print("student_id:", student_id)
                print("year:", year)
                print("semester:", semester)
                i = id//161
                print("major:", major[i])
                print("password:", password)
                print("count:", count)
                count += 1
                print("\n")
                data = [name, student_id, password, year, semester, major[i]]
                writer.writerow(data)
