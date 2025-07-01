function BUPT_format_converter(path,arg)
%Function: convert ppm into pgm
%Author jp2020213248
%   Input:
%       path: path of ppm file ready to be processed
%       arg: whether converting from RGB to YUV or YUV to RGB
%   Output:
%       output: processed matrix

%read the image
[I,w,h,level] = BUPT_ppmRead(path);

%show the output from RGB to YUV
if arg == 1
    %get the RGB components independently
    R = I(:,:,1);
    G = I(:,:,2);
    B = I(:,:,3);

    %get the value of YUV components
    Y =  0.257*R +0.504*G +0.098*B +16;
    U = -0.148*R -0.291*G +0.439*B +128;
    V =  0.439*R -0.368*G -0.071*B +128;

    Y = uint8(round(Y));
    U = uint8(round(U));
    V = uint8(round(V));
    output = cat(3,Y,U,V);
    figure, imshow(uint8(output)); title('Convert_to_YUV');

    %write the pgm image
    BUPT_pgmWrite(Y,"P2","converter_result");
    BUPT_ppmWrite(uint8(output),"P3","YUV_result");
    imwrite(output,'convert_to_YUV.ppm');

elseif strcmp(arg, 'inverse')
    I = double(I);
    %get the YUV components independently
    Y = I(:,:,1);
    U = I(:,:,2);
    V = I(:,:,3);
    
    %get the value of RGB components
    R = round( 1.164*Y + 1.596*V - 222.912 );
    G = round( 1.164*Y - 0.391*U - 0.813*V + 135.488 );
    B = round( 1.164*Y + 2.018*U - 276.928 );
    output = cat(3,R,G,B);
    figure, imshow(uint8(output)); title('Converted_to_RGB');
    BUPT_ppmWrite(uint8(output),"P3","RGB_result");
end


