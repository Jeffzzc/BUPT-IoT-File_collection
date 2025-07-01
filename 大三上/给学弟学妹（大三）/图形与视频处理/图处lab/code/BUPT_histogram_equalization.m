function BUPT_histogram_equalization(path)
%dunctionL histogram equalization
%Author jp2020213248
%   Input:
%       path: path of pgm file ready to be processed
%   Output:
%       image: output processed matrix being equalized

%read pgm image
[I,w,h,level] = BUPT_pgmRead(path);

%calculate the probability of each gray scale value
for i = 1:255
    probability(i) = length(find(I==(i-1)))/(w*h);
end

%calculate the distribution of image
probability = double(probability);
for i = 2:255
    probability(i) = probability(i-1) + probability(i);
end
probability(256) = 1;

for i = 1:w
    for j = 1:h
        I(i,j) = probability(I(i,j)+1) * 255; %reflect to new greyscale
    end
end

image = round(I);

%write the file into pgm format
BUPT_pgmWrite(image,"P2","histogram_equalization");