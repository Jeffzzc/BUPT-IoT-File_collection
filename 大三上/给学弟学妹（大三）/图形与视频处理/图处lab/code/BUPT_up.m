function BUPT_up( path, category, factor )
%Author jp2020213248
%   Input data:
%       path: the path of the pgm file
%       category:  'n' for nearest neighbour, 'b' for bilinear, 'both'
%       for showing both method of processing
%       factor: factor for up sampling
%   Output data:
%       output: processed matrix

%read the pgm file
[I,w,h,level] = BUPT_pgmRead(path);
subplot(1,2,1), imshow(uint8(I)); title('original');
if strcmp(category, 'n') || strcmp(category, 'nearest') %nearest tpe
    output = imresize(I, factor, 'nearest'); %nearest process
    %make zoom processing
    subplot(1,2,2),
    imshow(uint8(output)); title('Nearest Neighbour');
    %write into pgm type
    BUPT_pgmWrite(uint8(output),"P2",'upsample_result');
elseif strcmp(category, 'b') || strcmp(category, 'bilinear')
    output = imresize(I, factor, 'bilinear'); %bilinear process
    subplot(1,2,2), imshow(uint8(output)); title('Bilinear');
    %write into pgm type
    BUPT_pgmWrite(uint8(output),"P2",'upsample_result');
elseif strcmp(category, 'both')
    subplot(1,3,1), imshow(uint8(I)); title('Original image');
    %doing two process for comparing
    output{1} = imresize(I, factor, 'nearest');
    output{2} = imresize(I, factor, 'bilinear');
    subplot(1,3,2), imshow(uint8(output{1})); title('Nearest Neighbour');
    subplot(1,3,3), imshow(uint8(output{2})); title('Bilinear');
    %write into pgm type
    BUPT_pgmWrite(uint8(output{1}),"P2",'upsample_result1');
    BUPT_pgmWrite(uint8(output{2}),"P2",'upsample_result2');
else
    error('Please input ''n'' or ''b'' for the second parameter');
end
end