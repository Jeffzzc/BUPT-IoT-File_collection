function BUPT_rect(path,size)
%Author jp2020213248
%   Input:
%       path: the path of the pgm file
%       size: the size of filter
%   Output:
%       result: output processed matrix

%read the pgm file
[I,w,h,level] = BUPT_pgmRead(path);

gaussian = wgn(512,512,50,'dBm');
image = double(I) + gaussian; %add gaussian noise

filter = zeros(size,size);
for i = 1:size
    for j = 1:size
        filter(i,j) = 1/size^2; %normalize
    end
end

filter = double(filter);

%doing rectangular filter
result = conv2(image,filter,"same");
result = uint8(result);
%write the file into pgm format
BUPT_pgmWrite(result,"P2","rect_result");
