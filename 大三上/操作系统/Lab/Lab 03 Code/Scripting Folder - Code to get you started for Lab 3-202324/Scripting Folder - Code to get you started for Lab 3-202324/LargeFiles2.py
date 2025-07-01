import os
import glob

for filename in glob.glob('*'):
	print("File name", filename, "has size", str(os.path.getsize(filename)), "bytes")
        # the filesize function returns the size in bytes
        
