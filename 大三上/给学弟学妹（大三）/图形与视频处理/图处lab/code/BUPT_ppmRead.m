function [I,w,h,level] = BUPT_ppmRead(path)
%Function: ppm read
%Author jp2020213248
%   Input:
%       path: path of ppm file
%   Output:
%       I: output processed matrix
%       w: the width of image matrix
%       h: the height of image matrix
%       level: maximum level of image from the file

%open the file 
file = fopen(path);

%read the line, ignore the comment # until get type (p3 or p6)
line = fscanf(file,"%s",1);
while(line(1) == "#")
    fgets(file)
    line = fscanf(file,"%s",1);
end

%for P3, type = 0; for P6, type = 1
type = 0;

if (strcmp(line,"P3"))
    type = 0;
    disp('it is ppm file');
elseif (strcmp(line,"P6"))
    type = 1;
    disp('it is ppm file');
else
    disp('it is not ppm file');
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

%read P3 type file, it is PPM color image, stored in ASCII, 3 values RGB
%per pixel in 3 dimensions
if(type == 0)
    for i = 1:h
        for j = 1:w
            I(i,j,1) = fscanf(file,"%i",1);
            I(i,j,2) = fscanf(file,"%i",1);
            I(i,j,3) = fscanf(file,"%i",1);
        end
    end
end

%read P6 type file, it is PPM image stored in binary(compressed) format.
if(type == 1)
    fread(file,1);
    matrix = uint8(fread(file));
    index = 0;
    for i = 1:h
        for j = 1:w
            index = index +1;
            I(i,j,1) = matrix(index);
            index = index +1;
            I(i,j,2) = matrix(index);
            index = index +1;
            I(i,j,3) = matrix(index);
        end
    end
end

fclose(file);



