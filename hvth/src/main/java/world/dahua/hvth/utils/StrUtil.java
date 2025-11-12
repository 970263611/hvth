package world.dahua.hvth.utils;


/**
 * auth: dahua
 * time: 2025/11/10 10:31
 * desc: hutool不推荐使用了
 */
public class StrUtil {

    public static boolean isBlank(CharSequence str) {
        int length;
        if (str != null && (length = str.length()) != 0) {
            for (int i = 0; i < length; ++i) {
                if (!isBlankChar(str.charAt(i))) {
                    return false;
                }
            }

            return true;
        } else {
            return true;
        }
    }

    public static boolean isBlankChar(int c) {
        return Character.isWhitespace(c) || Character.isSpaceChar(c) || c == 65279 || c == 8234 || c == 0;
    }

    public static boolean isEmpty(CharSequence str) {
        return str == null || str.length() == 0;
    }

    public static String str(CharSequence cs) {
        return null == cs ? null : cs.toString();
    }

    public static String hide(CharSequence str, int startInclude, int endExclude) {
        return replace(str, startInclude, endExclude, '*');
    }

    public static String replace(CharSequence str, int startInclude, int endExclude, char replacedChar) {
        if (isEmpty(str)) {
            return str(str);
        } else {
            String originalStr = str(str);
            int[] strCodePoints = originalStr.codePoints().toArray();
            int strLength = strCodePoints.length;
            if (startInclude > strLength) {
                return originalStr;
            } else {
                if (endExclude > strLength) {
                    endExclude = strLength;
                }

                if (startInclude > endExclude) {
                    return originalStr;
                } else {
                    StringBuilder stringBuilder = new StringBuilder();

                    for (int i = 0; i < strLength; ++i) {
                        if (i >= startInclude && i < endExclude) {
                            stringBuilder.append(replacedChar);
                        } else {
                            stringBuilder.append(new String(strCodePoints, i, 1));
                        }
                    }

                    return stringBuilder.toString();
                }
            }
        }
    }

    public static int indexOf(CharSequence str, char searchChar) {
        return indexOf(str, searchChar, 0);
    }

    public static int indexOf(CharSequence str, char searchChar, int start) {
        return ((String) str).indexOf(searchChar, start);
    }

    public static String repeat(char c, int count) {
        if (count <= 0) {
            return "";
        } else {
            char[] result = new char[count];

            for (int i = 0; i < count; ++i) {
                result[i] = c;
            }

            return new String(result);
        }
    }
}
