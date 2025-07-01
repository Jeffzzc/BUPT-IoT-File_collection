import random
import string
import csv


def generate_name():
    name = ''
    for i in range(6):
        c = random.choice(string.ascii_letters)
        s = str(c)
        name += s
    return name


def generate_article():
    article = ''
    for i in range(100):
        c = random.choice(string.ascii_letters)
        s = str(c)
        article += s
    return article


if __name__ == '__main__':
    id = ['EBU5302', 'EBU5303', 'EBU5403',
          'EBU6018', 'EBU6230', 'EBU6304', 'EBU6305']
    curriculum = {'EBU5302': 'Telecommunications',
                  'EBU5303': 'Multimedia Fundamental',
                  'EBU5403': 'Internet Proticols',
                  'EBU6018': 'Advanced Transform',
                  'EBU6230': 'Image and Video Processing',
                  'EBU6304': 'Software Engineering',
                  'EBU6305': 'Interactive Media Design and Production'}

    schedule = {'EBU5302': '1.1;1.2;1.3',
                'EBU5303': '1.7;1.8;1.9',
                'EBU5403': '2.3;2.4;2.5',
                'EBU6018': '3.3;3.4;3.5',
                'EBU6230': '4.3;4.4;4.5',
                'EBU6304': '1.11;1.12;3.11;3.12',
                'EBU6305': '5.3;5.4;5.5'}

    header = ['id', 'name', 'year', 'semester', 'type', ' credit', 'major', 'day',
              'teacher name', 'teacher email', 'exam type', 'summary', 'aims', 'outcomes']
    with open('curriculum.csv', 'w', encoding='UTF8', newline='') as file:
        writer = csv.writer(file)
        writer.writerow(header)
        for ids in id:
            curriculum_id = ids
            # print('id:', curriculum_id)
            curriculum_name = curriculum[curriculum_id]
            # print('name:', curriculum_name)
            year = '1'
            semester = '2'
            type = 'compulsary'
            credit = '3'
            major = 'Telecommunications Engineering and Management'
            day = schedule[curriculum_id]
            teacher_name = generate_name()
            teacher_email = teacher_name + '@qmul.ac.uk'
            # print(teacher_email)
            exam_type = 'open-book'
            summary = generate_article()
            aims = generate_article()
            outcomes = generate_article()

            data = [curriculum_id, curriculum_name, year, semester, type,
                    credit, major, day, teacher_name, teacher_email, exam_type, summary, aims, outcomes]
            print(data)
            writer.writerow(data)
