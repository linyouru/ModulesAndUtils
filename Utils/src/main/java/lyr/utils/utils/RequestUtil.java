package lyr.utils.utils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class RequestUtil {
	/**
	 * <获取参数map>
	 * 
	 * @return 参数map
	 * @throws Exception
	 */
	public  static Map<String, Object> getParameterMap(HttpServletRequest request) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			Map<String, String[]> tempMap = request.getParameterMap();
			Set<String> keys = tempMap.keySet();
			for (String key : keys) {
				byte source[] = request.getParameter(key).getBytes("UTF-8");
				String modelname = new String(source, "UTF-8");
				resultMap.put(key, modelname);
			}
		} catch (Exception e) { 
			e.printStackTrace();
		}
		return resultMap;
	}
}
