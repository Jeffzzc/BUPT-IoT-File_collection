function BUPT_addSaltNoise(path)
%Author jp2020213248
%   Input:
%       path: the path of the pgm file
%   Output:
%       output: output processed matrix

%read the pgm file
[I,w,h,level] = BUPT_pgmRead(path);

%input the noise
input_noise = imnoise(I,"salt & pepper");

%processed output
output = input_noise;

% Caculate the values of MSE and PSNR
diff = input_noise-I;
diff = diff.*diff;

colorType = ndims(I);  % number of dimention
if colorType == 2 % for greyscale
    MSE=sum(sum(diff))/(w*h);
    PSNR=20*log10(255/(MSE^0.5));
elseif colorType == 3 % for RGB
    MSE=sum(sum(sum(diff))/(w*h*3));
    PSNR=20*log10(255/(MSE^0.5));
end

%print the value of MSE and PSNR
fprintf('MSE is%8.2f\n',MSE);
fprintf('PSNR is%8.2f\n',PSNR);

figure, imshow(uint8(output));
%write the file into pgm format
BUPT_pgmWrite(uint8(output),"P2",'addSaltNoise_result');