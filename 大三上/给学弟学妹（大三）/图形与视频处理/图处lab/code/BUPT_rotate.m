function [output,width_rotate,height_rotate] = BUPT_rotate(I,arg,w,h)
%Author jp2020213248
%   Input:
%       I: the input matrix of the pgm file
%       arg: the angle for rotating
%       w: the width of the image
%       h: the height of the image
%   Output:
%       output: output processed matrix

%rotation angle initialization
width_rotate = round(w*abs(cosd(arg))+h*abs(sind(arg)));
height_rotate = round(w*abs(sind(arg))+h*abs(cosd(arg)));

%create image
output = zeros(height_rotate,width_rotate);

%doing rotation
rotation = [cosd(arg),sind(arg);-sind(arg),cosd(arg)];

for j = 1:w
    for i = 1:h    
        r = rotation*[i-0.5*h,j-0.5*w]' + [0.5*height_rotate,0.5*width_rotate]';
        r = round(r)+1;
        output(r(1),r(2)) = I(i,j);
    end
end

for i = 1:height_rotate
    for j = 2:width_rotate-1
        if(output(i,j)== 0 && output(i,j-1)~=0 && output(i,j+1)~=0)
            output(i,j)=output(i,j-1);
        end
    end
end
%write the file into pgm format
BUPT_pgmWrite(uint8(output),"P2","image_rotate");
