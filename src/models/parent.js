const mongoose = require('mongoose')

const relationSchema = new mongoose.Schema({
  user1_id: {
    type: String,
    required: true
  },
  user2_id: {
    type: String,
    required: true
  },
  relation_type: {
    type: String,
    required: false
  }
}, { timestamps: true })

mongoose.pluralize(null)
const model = mongoose.model('Relation', relationSchema)

relationSchema.index({ user1_id: 1, user2_id: 1 }, { unique: true })

module.exports = model
