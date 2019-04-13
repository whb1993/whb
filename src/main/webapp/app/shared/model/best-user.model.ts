export interface IBestUser {
    id?: number;
    userName?: string;
    value1?: number;
    value2?: number;
    value3?: number;
    value4?: number;
    value5?: number;
    value6?: number;
    value7?: number;
    value8?: number;
    value9?: number;
    value10?: number;
    value11?: number;
    value12?: number;
    yuanyin?: string;
    des1?: string;
    des2?: string;
    des3?: string;
    des4?: string;
    des5?: string;
    des6?: string;
    des7?: string;
}

export class BestUser implements IBestUser {
    constructor(
        public id?: number,
        public userName?: string,
        public value1?: number,
        public value2?: number,
        public value3?: number,
        public value4?: number,
        public value5?: number,
        public value6?: number,
        public value7?: number,
        public value8?: number,
        public value9?: number,
        public value10?: number,
        public value11?: number,
        public value12?: number,
        public yuanyin?: string,
        public des1?: string,
        public des2?: string,
        public des3?: string,
        public des4?: string,
        public des5?: string,
        public des6?: string,
        public des7?: string
    ) {}
}
