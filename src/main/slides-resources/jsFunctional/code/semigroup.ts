interface SemiGroup {
    concat: (SemiGroup) => SemiGroup;
    // this.concat(x.concat(y)) = this.concat(x).concat(y)
}