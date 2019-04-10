import { category } from "./category.model";
import { comment } from "./comment.model";

export class thread {
    id: string;
    category: category;
    comments: comment[];
    headerComment: comment;
    title: string;
    numberComments: number;

    constructor(id: string, c: category, cms: comment[], headerCms: comment, t: string) {
        this.id = id;
        this.category = c;
        this.comments = cms;
        this.headerComment = headerCms;
        this.title = t;
    }

    set NumberComments(number: number) {
        this.numberComments = number
    }
}