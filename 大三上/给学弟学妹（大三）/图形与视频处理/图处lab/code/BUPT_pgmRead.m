function [I,w,h,level] = BUPT_pgmRead(path)
%Function: ppm read
%Author jp2020213248
%   Input:
%       path: path of pgm file
%   Output:
%       I: output processed matrix
%       w: the width of image matrix
%       h: the height of image matrix
%       level: maximum level of image from the file

%open the file 
file = fopen(path);

%read the line, ignore the comment # until get type
line = fscanf(file,"%s",1);
while(line(1) == "#")
    fgets(file)
    line = fscanf(file,"%s",1);
end

%for P2, type = 0; for P5, type = 1
type = 0;

if (strcmp(line,"P2"))
    type = 0;
    disp('it is pgm file');
elseif (strcmp(line,"P5"))
    type = 1;
    disp('it is pgm file');
else
    disp('it is not pgm file');
end

line = fscanf(file,"%s",1);
%read width of image from the file
while(line(1) == "#")
    fgets(file)
    line = fscanf(file,"%s",1);
end
w = str2num(line);

line = fscanf(file,"%s",1);
%read height of image from the file
while(line(1) == "#")
    fgets(file)
    line = fscanf(file,"%s",1);
end
h = str2num(line);

line = fscanf(file,"%s",1);
%read the maximum level of image from the file
while(line(1) == "#")
    fgets(file)
    line = fscanf(file,"%s",1);
end
level = str2num(line);

%read P2 type
if(type == 0)
    for i = 1:h
        for j = 1:w
            I(i,j) = fscanf(file,"%i",1);
        end
    end
end

%read P5 type
if(type == 1)
    fread(file,1);
    matrix = uint8(fread(file));
    index = 0;
    for i = 1:h
        for j = 1:w
            index = index +1;
            I(i,j) = matrix(index);
        end
    end
end

fclose(file);
