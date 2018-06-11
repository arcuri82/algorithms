LWD = 4

plot_x2_vs_10x9k <- function(){
    x = c(1 : 150)

    f = function(x){ return(x * x)}

    g = function(x){ return((10 * x) + 9000)}

    plot(x, g(x), ylim = c(0, 20000), ylab = "g(x) vs f(x)", xlab = "N")

    lines(x, f(x), type = "o", col = "blue")
}

plot_n_vs_2n <- function(){

    x = c(1 : 10)

    f = function(x){ return(x)}

    g = function(x){ return(2 * x)}

    plot(x, g(x), ylim = c(0, 20), ylab = "n vs 2n", xlab = "n", type = "o", lwd=LWD)

    lines(x, f(x), type = "o", col = "blue", lwd=LWD)
}

plot_n_vs_n2 <- function(){

    x = c(1 : 10)

    f = function(x){ return(x)}

    g = function(x){ return(x^2)}

    plot(x, g(x), ylim = c(0, 100), ylab = "n vs n^2", xlab = "n", type = "o", lwd=LWD)

    lines(x, f(x), type = "o",  col = "blue", lwd=LWD)
}

plot_10n_vs_11n <- function(){

    x = c(1 : 10)

    f = function(x){ return(10*x)}

    g = function(x){ return(11 * x)}

    plot(x, g(x), ylim = c(0, 110), ylab = "10n vs 11n", xlab = "n", type = "o", lwd=LWD)

    lines(x, f(x), type = "o", col = "blue", lwd=LWD)
}

plot_10np5_vs_12n <- function(){

    x = c(1 : 10)

    f = function(x){ return(10*x + 5)}

    g = function(x){ return(12 * x)}

    plot(x, g(x), ylim = c(0, 120), ylab = "10n + 5 vs 12n", xlab = "n", type = "o", lwd=LWD)

    lines(x, f(x), type = "o", col = "blue", lwd=LWD)
}

plot_n2p2n_vs_2n2 <- function(){

    x = c(1 : 10)

    f = function(x){ return(x^2 + 2 * x)}

    g = function(x){ return(2 * (x ^ 2))}

    plot(x, g(x), ylim = c(0, 120), ylab = "n^2 + 2n vs 2n^2", xlab = "n", type = "o", lwd=LWD)

    lines(x, f(x), type = "o", col = "blue", lwd=LWD)
}

plot_5_vs_n <- function(){

    x = c(1 : 10)

    f = function(x){ return(rep(5, length(x)))}

    g = function(x){ return(x)}

    plot(x, g(x), ylim = c(0, 10), ylab = "5 vs n", xlab = "n", type = "o", lwd=LWD)

    lines(x, f(x), type = "o", col = "blue", lwd=LWD)
}

plot_5_vs_7 <- function(){

    x = c(1 : 10)

    f = function(x){ return(rep(5, length(x)))}

    g = function(x){ return(rep(7, length(x)))}

    plot(x, g(x), ylim = c(0, 10), ylab = "5 vs 7", xlab = "n", type = "o", lwd=LWD)

    lines(x, f(x), type = "o", col = "blue", lwd=LWD)
}


plot_n2_vs_5n <- function(){

    x = c(1 : 10)

    f = function(x){ return(x^2)}

    g = function(x){ return(5 * x)}

    plot(x, g(x), ylim = c(0, 100), ylab = "n^2 vs 5n", xlab = "n", type = "o", lwd=LWD)

    lines(x, f(x), type = "o", col = "blue", lwd=LWD)
}