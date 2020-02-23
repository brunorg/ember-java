import Component from "@glimmer/component";
import { tracked } from "@glimmer/tracking";
import { action } from "@ember/object";
import { A } from "@ember/array";

export default class SelectModalComponent extends Component {
  @tracked value = "";
  @tracked confirmShown = false;
  @tracked results = A([]);

  constructor() {
    super(...arguments);
  }

  @action
  query() {
    var query = this.search;
    this.args.filter(query).then(results => this.results.addObjects(results));
  }

  @action
  launchModal() {
    var query = this.search;
    this.args.filter(query).then(results => this.results.addObjects(results));
    this.confirmShown = true;
  }

  @action
  submitConfirm() {
    // trigger action on parent component
    this.confirmShown = false;
  }

  @action
  cancelConfirm() {
    this.confirmShown = false;
  }
}
