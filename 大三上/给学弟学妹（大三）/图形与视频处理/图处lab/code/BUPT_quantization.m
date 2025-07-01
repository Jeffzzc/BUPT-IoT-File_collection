function BUPT_quantization(path,factor)
%Author jp2020213248
%   Input:
%       path: the path of the pgm file
%       factor: quantisation factor
%   Output:
%       image: output processed matrix

%read the pgm file
[I,w,h,level] = BUPT_pgmRead(path);
for i=1:w
    for j=1:h
        image(i,j)=floor(I(i,j)./factor).*factor; 
        %floor: doing round()
    end
end
%write the file into pgm format
BUPT_pgmWrite(image,"P2",'quantisation_result');

figure, imshow(uint8(image)); title('quantisation result');
end
