package cmp.sample.shared.designsystem.resource

fun buildStingsResources(): Map<Int, String> {
  val strs = mutableMapOf<Int, String>()
  val rs = MppR.string

  strs[rs.app_name] = "cmp-sample"
  return strs
}
