package mesosphere.marathon.client.model.v2;

public class GetAppRequest {
    private String cmd;
    private String[] embed;

    public GetAppRequest(String cmd) {
        this.cmd = cmd;
    }

    public GetAppRequest(String[] embed) {
        this.embed = embed;
    }

    public GetAppRequest(String cmd, String[] embed){
        this.cmd = cmd;
        this.embed = embed;
    }

    public String getCmd() {
        return cmd;
    }

    public String[] getEmbed(){
        return embed;
    }

	public static class Expander implements feign.Param.Expander {
		@Override
		public String expand(Object value) {
			if (value instanceof GetAppRequest) {
				GetAppRequest request = (GetAppRequest) value;
				StringBuilder builder = new StringBuilder();
				String cmd = request.getCmd();
				if (cmd != null && !cmd.trim().isEmpty()) {
					builder.append("cmd=").append(cmd);
				}
				String[] embed = request.getEmbed();
				if (embed != null) {
					for (int i = 0; i < embed.length; i++) {
						if (i > 0 || builder.length() > 0) {
							builder.append("&");
						}
						builder.append("embed").append("=").append(embed[i]);
					}
				}
				return builder.toString();
			}
			throw new IllegalArgumentException("Must provide object to type " + GetAppRequest.class);
		}

	}
}
