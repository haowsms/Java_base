package com.one;

/**
 * ip��/���������߽ӿ�, ��Ϊ��interfaceʵ��һ�������ڴ��ip��/����������ʵ����<br/>
 * Ҫ��'isInList'����Ϊ������ʱ�临�Ӷ�<br/>
 * Ҫ��'isInList'�ڲ�������ȫ�����ڴ棬������������ļ���ȡ; �����ʼ�������繹�캯�����ܴ�����(���ʼ��ʱ�ɴ��ļ���load ip�����б�)<br/>
 * ��������ϣ�������������������ǰ���£��ô˹�������֧�ֵ�ip�б����������ܴ�, �ڴ�ռ�þ�����С
 */
public interface IpList {
    /**
     * �ж�ָ����ipv4��ַ�Ƿ��ڵ�ǰ������
     * 
     * @param ip
     *            ָ����ip��ֵַ(v4)
     * @return true: �������У� false: ����������
     */
    boolean isInList(String ip);
}