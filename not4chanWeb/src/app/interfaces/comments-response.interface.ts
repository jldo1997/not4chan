import { comment } from "../model/comment.model";

export interface CommentResponse {
    rows: comment[];
    count: number;
}