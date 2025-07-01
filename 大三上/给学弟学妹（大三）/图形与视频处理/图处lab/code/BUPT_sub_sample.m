function BUPT_sub_sample( path, vertial_rate, horizontal_rate )
%Author jp2020213248
%   Input data:
%       path: the path of the pgm file
%       vertial_rate:  rate for sub-sampling in vertial direction
%       horizontal_rate: rate for sub-sampling in horizontal direction
%   Output data:
%       image: processed image matrix

%read the pgm file
[I,w,h,level] = BUPT_pgmRead(path);
column = ceil(w/vertial_rate);      % samples every column
row = ceil(h/horizontal_rate);      % samples every row
sampled_m = zeros(column, row);    % initialise the sampled matrix

% subsampling
for i = 1:column
    for j = 1:row
        sampled_m(i,j) = I( 1+vertial_rate*(i-1), 1+horizontal_rate*(j-1) );   
    end
end
% copy the sampled image into output
for i = 1:column
    for j = 1:row
        image(i,j) = sampled_m(i,j);    
    end
end
figure, imshow(uint8(image)); title(strcat('Subsampled result'));
%write the file into pgm format
BUPT_pgmWrite(image,"P2",'subsample_results');
end
