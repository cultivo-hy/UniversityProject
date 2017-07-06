from functools import reduce

mask1 = mask2 = polyred = None

def bitstring(x):  return bin(x)[2:]
def bitToDec(s):    return int(s, 2)
def hextobin(h):  return bin(int(h, 16))[2:].zfill(len(h) * 4)

def xor(a, b):
    result = []
    for i in range(1, len(b)):
        if a[i] == b[i]:
            result.append('0')
        else:
            result.append('1')
    return ''.join(result)

def getquotFrompoly(divident, divisor):
    pick = len(divisor)
    ml = len(divident) - pick
    quotient = ''
    remainder = divident[0: pick]
    while pick < len(divident):
        if remainder[0] == '1':
            remainder = xor(divisor, remainder) + divident[pick]
            quotient +='1'
        else:
            remainder = xor('0' * pick, remainder) + divident[pick]
            quotient +='0'
        pick += 1
    if remainder[0] == '1':
        remainder = xor(divisor, remainder)
        quotient += '1'
    else:
        remainder = xor('0' * pick, remainder)
        quotient += '0'
    return quotient

def setGF2(degree, irPoly):
    def i2P(sInt):
        return [(sInt >> i) & 1
                for i in reversed(range(sInt.bit_length()))]

    global mask1, mask2, polyred
    mask1 = mask2 = 1 << degree
    mask2 -= 1
    polyred = reduce(lambda x, y: (x << 1) + y, i2P(irPoly)[1:])
    
def multGF2(p1, p2):
    p = 0
    while p2:
        if p2 & 1:
            p ^= p1
        p1 <<= 1
        if p1 & mask1:
            p1 ^= polyred
        p2 >>= 1
    return p & mask2

def getinverse(f, d):
    x1, x2, x3 = 1, 0, f
    y1, y2, y3 = 0, 1, d
    boolean =1
    while boolean :
        if x3 == 0:
            boolean = 0
            return 0
        elif x3 == 1:
            boolean = 0
            return x2
        a , b = bitstring(x3) , bitstring(y3)
        value = getquotFrompoly(a, b)
        quot = bitToDec(value)
        t1, t2, t3 = x1 ^ multGF2(quot,y1) , x2 ^ multGF2(quot,y2) , x3 ^ multGF2(quot,y3)
        x1, x2, x3 = y1, y2, y3
        y1, y2, y3 = t1, t2, t3
        
def getinverselist():
    setGF2(9, 0b100011011)
    list = []
    for i in xrange(256):
        list.append(hex(getinverse(283,i)))
    return list

def printlist(list):
    for i in xrange(16):
        for j in xrange(16):
            print list[16*i+j],
        print

def affine_transformation(b):
    var = hextobin(b)
    list = []
    string=[]
    index = 8
    if len(var) == 12 :
        index  -= 4      
    for it in range(8):
        string.append(var[it+index])
    c = '11000110'
    for i in range(8):
        var = (int)(string[i]) ^ (int)(string[(i + 4) % 8])
        var ^=  (int)(string[(i + 5) % 8])
        var ^= (int)(string[i]) ^ (int)(string[(i + 6) % 8])
        var ^= (int)(string[i]) ^ (int)(string[(i + 7) % 8])
        var ^= (int)(c[i])
        list.append(var.__str__())
    for i in range(8):
        string[i] = list[(i+4) % 8]
    tmp = bitToDec(''.join(string))
    return hex(tmp)

def getsbox(list):
    tmp = []
    for i in xrange(len(list)):
        tmp.append(affine_transformation(list[i]))
    return tmp

if __name__ == "__main__":
    print "------------------------------------------------------------------------------"
    print "                               S-box generator"
    print "------------------------------------------------------------------------------"
    print "                             inverse of 0x00 ~ 0xff"
    print "------------------------------------------------------------------------------"
    list = getinverselist()
    printlist(list)
    print "------------------------------------------------------------------------------"
    print "                             Affine Transform finish "
    print "------------------------------------------------------------------------------"
    list = getsbox(list)
    print "------------------------------------------------------------------------------"
    print "                                   S - box  "
    print "------------------------------------------------------------------------------"
    printlist(list)
    print "------------------------------------------------------------------------------"
    print "                                S - box Finish"
    print "------------------------------------------------------------------------------"
