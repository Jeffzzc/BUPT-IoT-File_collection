function BUPT_edge_pgm(path)
%Author jp2020213248
%   Input:
%       path: path of pgm file ready to be processed
%   Output:
%       image: processed image after doing edge detection

%read the pgm image
[I,w,h,level] = BUPT_pgmRead(path);

%sobel operator
sobel_h = [-1,0,1;-2,0,2;-1,0,1];
sobel_w = [1 2 1; 0 0 0;-1 -2 -1];

%doing detection using sobel
x = conv2(I,sobel_w,"same");
y = conv2(I,sobel_h,"same");

image = abs(x) + abs(y);
image = uint8(image);

%write the pgm file
BUPT_pgmWrite(image,"P2","sobel");

%roberts operator
roberts_h = [0,-1;1,0];
roberts_w = [1,0;0,-1];

%doing detection using roberts
x = conv2(I,roberts_w,"same");
y = conv2(I,roberts_h,"same");

image = abs(x) + abs(y);
image = uint8(image);

%write the pgm file
BUPT_pgmWrite(image,"P2","roberts");

%prewitt operator
prewitt_h = [-1,-1,-1;0,0,0;1,1,1];
prewitt_w = [-1,0,1;-1,0,1;-1,0,1];

%doing detection using prewitt
x = conv2(I,prewitt_w,"same");
y = conv2(I,prewitt_h,"same");

image = abs(x) + abs(y);
image = uint8(image);

%write the pgm file
BUPT_pgmWrite(image,"P2","prewitt");