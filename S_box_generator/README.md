This is S_Box_Generation code which i submit in the Computer Securiy class of PNU.

This code is not good for study. becuase i'm not good at python. so just understand the step of generation.

To make a S_Box, We should get a imversed list(value 0x00 to 0xff) and do Affine transformation.


[Main Function info]

getInverseUsingByEEA - function is based on Extend Euclid Algorithm. 
 
getquotFromply - function is based on CRC function. change a little to get quotient. (basically CRC make remainder)
                 Reference site is here. ( http://www.geeksforgeeks.org/modulo-2-binary-division/ )

Related Reference 
https://github.com/pcaro90/Python-AES-base ( This can help to understand step of AES)
