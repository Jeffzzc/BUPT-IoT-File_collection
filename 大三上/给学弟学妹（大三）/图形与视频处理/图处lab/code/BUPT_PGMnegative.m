function BUPT_PGMnegative(p)
%Author jp2020213248
%   Input:
%       p: path of pgm file
%   Output:
%       image: output processed matrix

% read the pgm file from the path
[I,w,h,level] = BUPT_pgmRead(p);
%doing negative
image = 255 - I;
%write the file into pgm format
BUPT_pgmWrite(image,"P2","negative_PGM");