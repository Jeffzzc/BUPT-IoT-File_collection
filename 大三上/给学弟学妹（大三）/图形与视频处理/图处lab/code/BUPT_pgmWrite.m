function BUPT_pgmWrite(I,type,name)
%Function: pgm write
%Author jp2020213248
%   Input:
%       I: the input matrix of the pgm file
%       type: P2 or P5
%       name: file name

%open the file
file = fopen(name+".pgm",'a');

%read the image 
[w,h] = size(I);
level = max(max(I));
comment = "# image created by Tangxun Wu jp2020213248";

%write p2 type file 
if (strcmp(type,"P2")) 
    fprintf(file,'%s\n',"P2");
    fprintf(file,'%s\n',comment);
    fprintf(file,'%d ',h);
    fprintf(file,'%d\n',w);
    fprintf(file,'%d\n',level);
    for i = 1:w
        for j = 1:h
            fprintf(file,"%d ",I(i,j));
        end
    end
%write p5 type file 
elseif (strcmp(type,"P5")) 
    fprintf(file,'%s\n',"P5");
    fprintf(file,'%s\n',comment);
    fprintf(file,'%d ',h);
    fprintf(file,'%d\n',w);
    fprintf(file,'%d\n',level);
    for i = 1:w
        for j = 1:h
            fwrite(file,I(i,j),'uint8');
        end
    end
end
fclose(file);