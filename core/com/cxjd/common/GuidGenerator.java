package com.cxjd.common;

import java.util.UUID;

/**
 * ����GUID
 * @author ���ڳ�
 * @since 2013-05-11
 *
 */
public class GuidGenerator
{
  public static String getGuid()
  {
    UUID uuid = UUID.randomUUID();
    return uuid.toString();
  }
}