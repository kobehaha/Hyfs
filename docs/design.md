# Design

## NameNode Ha

master ---->  slave protcol
master save all the meta info, And not only Memory.

## File split block

Key to support large file, we can define 2G is standard.

## File meta Info

All file info save NameNode

## Write 

1 client write one , server copy each other


## Namespace

    kv
    
    /home/file1/
            /block1
            /block2
         /file2/
            /block2
            /block3
         /file3
            /block3
            /block100
            
            
            