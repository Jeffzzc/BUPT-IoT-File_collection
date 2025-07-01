function BUPT_histogram_modeling( path1, path2 )
%Author jp2020213248
%   Input:
%       path1: path of pgm file being modelled
%       path2: path of pgm file ready to be processed
%   Output:
%       output: output processed matrix being modelled

% read the pgm file from the path
[I1,w1,h1,level1] = BUPT_pgmRead(path1);
[I2,w2,h2,level2] = BUPT_pgmRead(path2);
in1 = uint8(I1);
in2 = uint8(I2);

his=imhist(in1); %histogram
pi=his/(h1*w1); %probability

cd1=[];
% cumulative distribution probability
for i=1:256
    cd1=[cd1 sum(pi(1:i))];
end

%calculate probability
histogram=imhist(in2);
pt=histogram/(h2*w2);

cd2=[];
% cumulative distribution probability
for i=1:256
    cd2=[cd2, sum(pt(1:i))];
end

index=[];
for i=1:256
    temp{i}=cd1-cd2(i); %difference
    temp{i}=abs(temp{i}); %change to absolute value
    [a, index(i)]=min(temp{i}); %determine the minimum difference
end

image_noise=zeros(h2,w2);
for i=1:h2
    for j=1:w2
        image_noise(i,j)=index(in2(i,j)+1)-1; %create histogram
    end
end

out1 = image_noise;

%write the file into pgm format
BUPT_pgmWrite(uint8(out1),"P2","histogram_modeling");

%create the exponential distribution
for i=-255:0
    e(i+256)=1.6^(i/16);
end
%create the cumulative probability distribution
cd1=[];
for i=1:256
    cd1 = [cd1, e(i)];
end
%normalize the pixel probability
histogram=imhist(in2);
pt=histogram/(h2*w2);
%create the cumulative probability distribution
cd2=[];
for i=1:256
    cd2=[cd2, sum(pt(1:i))];
end

for i=1:256
    temp{i}=cd1-cd2(i);
    temp{i}=abs(temp{i});
    [a, index(i)]=min(temp{i});
end

image_noise=zeros(h2,w2);
for i=1:h2
    for j=1:w2
        image_noise(i,j)=index(in2(i,j)+1)-1;
    end
end
%the image after modeling by exponential
out2 = image_noise; 

%write the file into pgm format
BUPT_pgmWrite(uint8(out2),"P2","histogram_exponential");

end
