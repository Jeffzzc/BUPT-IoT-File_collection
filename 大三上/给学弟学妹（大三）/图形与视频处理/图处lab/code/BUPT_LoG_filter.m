function BUPT_LoG_filter( path, sigma, size )
%Author jp2020213248
%   Input:
%       path: path of pgm file
%       sigma: the standard variance of gaussian noise
%       size: size of kernel
%   Output:
%       output: output processed matrix

% read the pgm file from the path
[I,w,h,~] = BUPT_pgmRead(path);

title_string = strcat('sigma=', num2str(sigma));

if nargin == 2  %the input number
    size = 6*sigma + 1; % analytical (empirical)
else
	title_string = strcat(title_string, ',  k_size=', num2str(size));
end

%kernel (size*size)
kernel = [size, size]; 

% the Gaussian filter
range = (kernel-1)/2;
std = sqrt(sigma);
[coor_x,coor_y] = meshgrid(-range(2):range(2),-range(1):range(1));
filter = -1/(pi*(std^4))*(1-(coor_x.*coor_x+coor_y.*coor_y)/(2*std^2)).*exp(-(coor_x.*coor_x+coor_y.*coor_y)/(2*std^2));

% go though the filter
new_input = filter2(filter,I);

% judge and define the value
output = zeros(h,w);
transform = 0.75*mean2(abs(new_input(2:h-1,2:w-1)));
for i=2:h-1
    for j=2:w-1
        if new_input(i,j)<0
            if new_input(i+1,j)>0||new_input(i,j+1)>0||new_input(i,j-1)>0||new_input(i-1,j)>0
                if max([new_input(i+1,j) new_input(i,j+1) new_input(i,j-1) new_input(i-1,j)])-new_input(i,j)>transform
                    output(i,j)=1;
                end
            end
        else if new_input(i,j)==0
                if (new_input(i+1,j)*new_input(i-1,j)<0&&abs(new_input(i+1,j)-new_input(i-1,j))>2*transform)||(new_input(i,j+1)*new_input(i,j-1)<0&&abs(new_input(i,j+1)-new_input(i,j-1))>2*transform)
                    output(i,j)=1;
                end
            end
        end
    end
end

% do thresholding
output = output*255;  

figure, imshow(uint8(output)); title(title_string);

%write the file into pgm format
BUPT_pgmWrite(uint8(output),"P2",'LoG_filter_results');

end

