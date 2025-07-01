function [ image ] = BUPT_transform( I, th1, arg, procedure )
%Author jp2020213248
%   Input:
%       I: input image matrix (read from file path)
%       th1: the angle for rotation
%    	arg: the angle for skewing
%    	procedure: doing which (row/skew) first
%   Output:
%    	image: output image matrix (doing transform)

% read the image height and length
[height, width] = size(I);

% correct the angle
rot=th1/180*pi;
skew=-(90-arg)/180*pi;

%rotate matrix
rot_matrix=[cos(rot) -sin(rot)  0;
   sin(rot)  cos(rot)  0;
   0            0            1]; 
%skew
if skew==0  
    skew_matrix=[1 0 0;0 1 0;0 0 1];
else
    skew_matrix=[1 1/(tan(skew)) 0;0 1 0;0 0 1];
end

%skew first
if nargin == 4 && strcmp(procedure, 's')
    transform_matrix=rot_matrix*skew_matrix;
else
    transform_matrix=skew_matrix*rot_matrix; %rotate first (default sort)
end

%build transform matrix
C1=transform_matrix*[1 1 1]';
C2=transform_matrix*[width height 1]';
C3=transform_matrix*[1 height 1]';
C4=transform_matrix*[width 1 1]';

%adjust size
max_new=max(C1,max(C2,max(C3,C4)));
min_new=min(C1,min(C2,min(C3,C4)));
max_new=round(max_new);
min_new=round(min_new);

% doing initialization
I_new = zeros(max_new(2)-min_new(2)+1,1+max_new(1)-min_new(1)); %0 matrix

% rotation
for i=1:height
    for j=1:width
        coor=transform_matrix*[j i 1]';
        I_new(round(coor(2))-min_new(2)+1,1+round(coor(1))-min_new(1))=I(i,j);
    end
end

wnew=max_new(1)-min_new(1)+1;
hnew=max_new(2)-min_new(2)+1;

% solve the black pixels problem (answer the question in report)
for i=1:hnew
    for j=1:wnew
        if I_new(i,j)==0
            CoorOld=round(inv(transform_matrix)*[j+min_new(1)-1 i+min_new(2)-1 1]');
            if CoorOld(1)>0&&CoorOld(2)>0&&CoorOld(1)<width&&CoorOld(2)<height
                I_new(i,j)=I(CoorOld(2),CoorOld(1));
            end
        end
    end
end

%output matrix
image = I_new;

figure, imshow(uint8(image));
%write the file into pgm format
BUPT_pgmWrite(uint8(image),"P2","image_rotate_skew");

end