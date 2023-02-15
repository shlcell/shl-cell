package com.shl.demo.gpu;

import com.shl.demo.domain.GpuInfo;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class GetGpu {
    public static List<GpuInfo> getGPUUsagePercentage() throws IOException {
        Process process = null;
        try {
            if (JudgeSystem.isLinux()) {
                String[] shell = {"/bin/bash", "-c", "nvidia-smi"};
                process = Runtime.getRuntime().exec(shell);
            } else if (JudgeSystem.isWindows()) {
                long start = System.currentTimeMillis();
//                process = Runtime.getRuntime().exec("C:\\Windows\\System32\\DriverStore\\FileRepository\\nvlt.inf_amd64_e7444925b6f55a93\\nvidia-smi.exe");
//                process = Runtime.getRuntime().exec("C:\\Windows\\System32\\DriverStore\\FileRepository\\nvlt.inf_amd64_21b51575168be509\\nvidia-smi.exe");
                process = Runtime.getRuntime().exec("C:\\Windows\\System32\\DriverStore\\FileRepository\\nvlt.inf_amd64_11d334744b45e20c\\nvidia-smi.exe");
//                process = Runtime.getRuntime().exec("nvidia-smi.exe");
                long end = System.currentTimeMillis();
                log.info("Java调用程序耗时：{}毫秒", end - start);
            } else {
                throw new Exception("不在系统支持范围内");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } finally {
            if (process != null) {
                process.getOutputStream().close();
            }
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        StringBuffer stringBuffer = new StringBuffer();
        String line = "";
        while (null != (line = reader.readLine())) {
            stringBuffer.append(line + "\n");
        }
        String gpus = null;
        gpus = stringBuffer.toString();
        System.out.println(gpus);
        //分割废物信息
        String[] split = gpus.split("\\|===============================\\+======================\\+======================\\|");
        String[] gpusInfo = split[1].split("                                                                               ");
        // 分割多个gpu
        String[] gpuInfo = gpusInfo[0].split("\\+-------------------------------\\+----------------------\\+----------------------\\+");
        List<GpuInfo> gpuInfoList = new ArrayList<>();
        for (int i = 0; i < gpuInfo.length - 1; i++) {
            GpuInfo gpuInfo1 = new GpuInfo();
            String[] nameAndInfo = gpuInfo[i].split("\n");
            //只要第二块的数据
            String[] split1 = nameAndInfo[1].split("\\|")[1] // 0  TITAN V             Off
                    .split("\\s+");//去空格
            gpuInfo1.setNumber(Integer.parseInt(split1[1]));
            StringBuffer name = new StringBuffer();
            for (int j = 0; j < split1.length - 1; j++) {
                if (j > 1 && j != split1.length) {
                    name.append(split1[j] + " ");
                }
            }
            gpuInfo1.setName(name.toString());
            String[] info = nameAndInfo[2].split("\\|")[2].split("\\s+");
            gpuInfo1.setUsedMemory(info[1]);
            gpuInfo1.setTotalMemory(info[3]);
            int useable = Integer.parseInt(gpuInfo1.getTotalMemory().split("MiB")[0]) - Integer.parseInt(gpuInfo1.getUsedMemory().split("MiB")[0]);
            gpuInfo1.setUseAbleMemory(useable + "MiB");
            Double usageRate = Integer.parseInt(gpuInfo1.getUsedMemory().split("MiB")[0]) * 100.00 / Integer.parseInt(gpuInfo1.getTotalMemory().split("MiB")[0]);
            gpuInfo1.setUsageRate(usageRate);
            gpuInfoList.add(gpuInfo1);

        }
        return gpuInfoList;

    }

    public static void main(String[] args) throws IOException {
        System.out.println(getGPUUsagePercentage());
    }
}

