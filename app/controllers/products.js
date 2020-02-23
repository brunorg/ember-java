import Controller from "@ember/controller";
import { action } from "@ember/object";

export default class ProductsController extends Controller {
  @action
  remove(record) {
    return record.destroyRecord();
  }
}
