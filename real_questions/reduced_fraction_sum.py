def gcd(a, b):
    while b:
        a, b = b, a%b
    return a

def suming(expression):
	a,b = expression.split('+')
	a_up,a_down = a.split('/')
	b_up,b_down = b.split('/')
	a_up,a_down = int(a_up),int(a_down)
	b_up,b_down = int(b_up),int(b_down)
	down = a_down*b_down
	up = a_up*b_down+b_up*a_down
	if up==down:
		return "1/1"
	GCD = gcd(up,down)
	if GCD==1:
		return str(up)+'/'+str(down)
	return str(up/GCD)+'/'+str(down/GCD)

def sums(expressions):
	return [suming(e) for e in expressions]


print(sums(['17/16+18/19','20/7+213712/123']))