function BUPT_edge_ppm(path)
%Author jp2020213248
%   Input:
%       path: path of ppm file ready to be processed
%   Output:
%       image: processed image after doing edge detection

%read the ppm image and get RGB components
[I,w,h,level] = BUPT_ppmRead(path);
R = I(:,:,1);
G = I(:,:,2);
B = I(:,:,3);

%sobel operator
sobel_h = [-1,0,1;-2,0,2;-1,0,1];
sobel_w = [1 2 1; 0 0 0;-1 -2 -1];

%doing detection using sobel
sobel_R = abs(conv2(R,sobel_w,"same")) + abs(conv2(R,sobel_h,"same"));
sobel_G = abs(conv2(G,sobel_w,"same")) + abs(conv2(G,sobel_h,"same"));
sobel_B = abs(conv2(B,sobel_w,"same")) + abs(conv2(B,sobel_h,"same"));
image = cat(3,sobel_R,sobel_G,sobel_B);
image = uint8(image);

%write the ppm file
BUPT_ppmWrite(image,"P3","sobel");

%roberts operator
roberts_h = [0,-1;1,0];
roberts_w = [1,0;0,-1];

%doing detection using roberts
roberts_R = abs(conv2(R,roberts_w,"same")) + abs(conv2(R,roberts_h,"same"));
roberts_G = abs(conv2(G,roberts_w,"same")) + abs(conv2(G,roberts_h,"same"));
roberts_B = abs(conv2(B,roberts_w,"same")) + abs(conv2(B,roberts_h,"same"));
image = cat(3,roberts_R,roberts_G,roberts_B);
image = uint8(image);

%write the ppm file
BUPT_ppmWrite(image,"P3","roberts");

%prewitt operator
prewitt_h = [-1,-1,-1;0,0,0;1,1,1];
prewitt_w = [-1,0,1;-1,0,1;-1,0,1];

%doing detection using prewitt
prewitt_R = abs(conv2(R,prewitt_w,"same")) + abs(conv2(R,prewitt_h,"same"));
prewitt_G = abs(conv2(G,prewitt_w,"same")) + abs(conv2(G,prewitt_h,"same"));
prewitt_B = abs(conv2(B,prewitt_w,"same")) + abs(conv2(B,prewitt_h,"same"));
image = cat(3,prewitt_R,prewitt_G,prewitt_B);
image = uint8(image);

%write the ppm file
BUPT_ppmWrite(image,"P3","prewitt");