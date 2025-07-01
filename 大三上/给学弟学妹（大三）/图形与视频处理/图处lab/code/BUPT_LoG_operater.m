function BUPT_LoG_operater(path,sigma)
%Author jp2020213248
%   Input:
%       path: path of pgm file
%       sigma: the standard variance of gaussian noise
%   Output:
%       result: output processed matrix

% read the pgm file from the path
[I,w,h,level] = BUPT_pgmRead(path);
image = double(I);

% Create the gaussian Filter.
Gaussian = fspecial('gaussian', 3, sigma);

% Define the Laplacian Filter.
Lap= fspecial('log');
%Lap=[0 -1 0; -1 4 -1; 0 -1 0];

% Convolve the noisy image
% with Gaussian Filter first.
result1 = conv2(image,Gaussian,"same");
% Convolve the resultant
% image with Laplacian filter
result2 = conv2(result1,Lap,"valid");

%change into unit8 format
result = uint8(result2);

% Display the Laplacian of
% Gaussian resultant image.
BUPT_pgmWrite(result,"P2","LoG_operater_result");
