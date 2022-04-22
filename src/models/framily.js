const mongoose = require('mongoose')

const familyNucleusSchema = new mongoose.Schema({
  user_id: {
    type: String,
    required: true
  },
  nucleus_id: {
    type: String,
    required: true
  }
}, { timestamps: true })

familyNucleusSchema.index({ user_id: 1 })
familyNucleusSchema.index({ user_id: 1, nucleus_id: 1 }, { unique: true })

mongoose.pluralize(null)
const model = mongoose.model('FamilyNucleus', familyNucleusSchema)

module.exports = model
