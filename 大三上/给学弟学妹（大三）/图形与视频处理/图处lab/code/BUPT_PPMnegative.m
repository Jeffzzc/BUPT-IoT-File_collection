function BUPT_PPMnegative(p)
%Author jp2020213248
%   Input:
%       p: path of ppm file
%   Output:
%       image: output processed matrix

% read the ppm file from the path
[I,w,h,level] = BUPT_ppmRead(p);
%doing negative
image = 255 - I;
%write the file into ppm format
BUPT_ppmWrite(image,"P3","negative_PPM");