function BUPT_lowpass(path, power, sigma)
%Author jp2020213248
%   Input:
%       path: path of pgm file
%       power: the power of gaussian noise
%       sigma: the standard variance of gaussian noise
%   Output:
%       result: output processed matrix

% read the pgm file from the path
[I,w,h,level] = BUPT_pgmRead(path);

noise = imnoise(I,'gaussian', 0, 0.05);%add noise
if length(power)==2 
    size = (power-1)/2;
else
    size = [(power-1)/2 (power-1)/2];
end

%variance of gaussian noise
std2 = sigma^2;

%making Gaussian filter operator (kernel)
[x, y] = meshgrid(-size(2):size(2),-size(1):size(1));
arg = -(x.*x + y.*y)/(2*std2);
trans = exp(arg);
trans(trans<eps*max(trans(:))) = 0;
sumh = sum(trans(:));

if sumh ~= 0,
    trans  = trans/sumh;
end

%doing filter
result = imfilter(noise,trans,'symmetric');

%change into unit8 format
result = uint8(result);

%write the file into pgm format
BUPT_pgmWrite(result,"P2","lowpass_result");
