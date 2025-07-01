function BUPT_ppmWrite(I,type,name)
%Function: ppm write
%Author jp2020213248
%   Input:
%       I: the input matrix of the ppm file
%       type: P3 or P6
%       name: file name

%open the file
file = fopen(name+".ppm",'wb');

%read the image 
[w,h,c] = size(I);
level = max(max(max(I)));
comment = "# image created by Tangxun Wu jp2020213248";

%write p3 type file 
if (strcmp(type,"P3")) 
    fprintf(file,'%s\r\n',"P3");
    fprintf(file,'%s\r\n',comment);
    fprintf(file,'%d ',h);
    fprintf(file,'%d\r\n',w);
    fprintf(file,'%d\r\n',level);
    for i = 1:w
        for j = 1:h
            fprintf(file,'%d ',I(i,j,1));
            fprintf(file,'%d ',I(i,j,2));
            fprintf(file,'%d ',I(i,j,3));
        end
    end
%write p6 type file 
elseif (strcmp(type,"P6")) 
    fprintf(file,'%s\n',"P6");
    fprintf(file,'%s\n',comment);
    fprintf(file,'%d ',h);
    fprintf(file,'%d\n',w);
    fprintf(file,'%d\n',level);
    for i = 1:w
        for j = 1:h
            fwrite(file,I(i,j,1),'uint8');
            fwrite(file,I(i,j,2),'uint8');
            fwrite(file,I(i,j,3),'uint8');
        end
    end
end
fclose(file);
end