function BUPT_histogram(path)
%function generate histogram of images
%Author jp2020213248
%   Input:
%       path: path of pgm file ready to be processed
%   Output:
%       img: processed matrix

%read pgm image
[I,w,h,level] = BUPT_pgmRead(path);

%count the number of appearence of each gray scale value
img = zeros(1,256);
for i = 1:255
    img(i) = length(find(I==(i-1)));
end
figure
bar(0:255,img); %draw the bar chart
title('histogram');
end