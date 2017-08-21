
x = c(1:150)

f = function(x){ return(x * x)}

g = function(x){ return((10*x) + 9000)}

plot(x, g(x), ylim=c(0,20000), ylab="g(x) vs f(x)", xlab="N")

lines(x, f(x), type = "o", col = "blue")