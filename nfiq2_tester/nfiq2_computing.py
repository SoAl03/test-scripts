import subprocess
import glob


def read_output(fin, file):
    with open('output.txt' , 'r') as doc:
        for line in doc:
            fin.write(file + f'\t{line}')


def call_nfiq2(path_nfiq2, file, fin):
    p = subprocess.Popen([path_nfiq2, '-i', file, '-o', 'output.txt'], stdin = subprocess.PIPE, stdout = subprocess.PIPE )
    
    p.stdin.write(b'y\n')
    p.stdin.write(b'y\n')
    p.stdin.close()
    
    stdin, stderr = p.communicate()
        
    read_output(fin, file) 


def find_images(file):
    return [dirs for dirs in glob.glob(path_test_data + '/**/*.*', recursive=True) if '.txt' not in dirs]
    

def main(path_test_data, path_nfiq2):
    with open('result.txt', 'w') as fin:
        for file in find_images(path_test_data):
            call_nfiq2(path_nfiq2, file, fin)


if __name__=="__main__": 
    '''Please change path_nfiq2 with the path from nfiq2.exe'''
    path_nfiq2 = r"C:\Program Files\NFIQ 2\bin\nfiq2.exe"
    
    ###################################
    path_test_data = r"../test_data/"
    main(path_test_data, path_nfiq2)
    
