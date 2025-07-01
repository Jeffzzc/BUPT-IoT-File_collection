import random
import string
import csv

def generate_grate():
    grade = random.randrange(50,100)
    print(grade)
    return grade

if __name__ == '__main__':
    id = ['EBU5302', 'EBU5303','EBU5403', 'EBU6018', 'EBU6230', 'EBU6304', 'EBU6305']
    header = ['curriculum_id', 'student_id', 'grade']
    with open('grade.csv', 'w', encoding='UTF8', newline='') as file:
        writer = csv.writer(file)
        writer.writerow(header)
        for ids in id:
            print(ids)
            for i in range(0,322):
                grade = generate_grate()
                student_id = 2021213000 + i
                data = [ids, student_id, grade]
                writer.writerow(data)
