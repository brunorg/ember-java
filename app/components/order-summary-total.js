import Component from '@glimmer/component';
import { tracked } from "@glimmer/tracking";

export default class OrderSummaryTotalComponent extends Component {

  @tracked customerLoaded = false;
  @tracked customerFullName = ""

  constructor() {
    super(...arguments);
    this.loadCustomer();
  }

  async loadCustomer() {
    await this.args.order.customer.firstObject;
    this.customerLoaded = true;
    this.customerFullName = this.args.order.customer.get("firstName");
  }

  get customerFullName() {
    return (this.customerLoaded ? this.args.order.customer.firstName : "");
  }

}
