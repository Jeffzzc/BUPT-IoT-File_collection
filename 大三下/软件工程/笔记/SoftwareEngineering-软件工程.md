# Introduction

## software types

general: developed for a general market. (ps, office)

custom: developed for a particular aim. (embeded system)

## good software features

delivers required functionality

usable

efficient

dependable

maintainable

understandable

cost-effective

security

## what is software engineering?

using proper theories, tools, methods(organized and systematic), constraints to develop high quantity software.

## 4 layers

Tools, method, process, quality focus.

## Why important?

large scale projects are hard to finish in time properly because of time, budget, disaster, etc. guiding scientific principles and universally applicable methods can reduce practice and frequent failures.

## General issues that affect software

Heterogeneity (distributed systems, different type of devices), business and social change, security and trust, scale.

# Software process

a set of structured activities to produce software.

includes: required specification, development(analysis, design, implementation), validation (test), evolution.

- required specification: what should this system do? a complete desc of the problem and environment constraints. contains: system function, future extension, documentation amount, time and preformance response
- analysis: create a conceptual model.
- design: implementable model (architecture, components of this system).
- implementation: implementation of interfaces.
- testing: unit, functional, integration, system, acceptance. test and implementation should run in parallel.
- deployment: install, run and test on real environment.
- evolution: corrective, adaptive, perfective, preventive.

## process models

a abstraction of process. activities must be modelled to be managed.

### waterfall

![1685629715429](https://raw.githubusercontent.com/Jingqing3948/FigureBed/main/mdImages/1685629715429.png)

易于管理 monitor，结构化很好。前面的模块必须完成才能跳到下一个，也就要求我们在最初设计的时候对需求很清楚，但是很难不遇到一些问题，很难按照正常流程正常走。

### evolutionary development

![1685629943510](https://raw.githubusercontent.com/Jingqing3948/FigureBed/main/mdImages/1685629943510.png)

交互性的活动，及时的反馈。核心需求能很好的满足，但是扩展性很差，结构性很差，缺少过程中的可见性。适合小型项目。

### rational unified process

![1685630556923](https://raw.githubusercontent.com/Jingqing3948/FigureBed/main/mdImages/1685630556923.png)

inception: 分析项目可行性。

elaboration: 分析风险，架构。

construction: iterative 迭代开发到 beta 版本为止。

transition: 交付到客户手中。

通用的完整流程，动态。但是开销大了。

### Agile Software Development 

现代软件开发流程：敏捷开发。

Rapid software development 快速开发：争取每一版都交一个可以交付的东西给客户。

敏捷开发：系统在一次一次迭代小增量过程中建立而成。specification, design, implementation, testing 并行。专注于代码，轻量级代码频繁发布，维护简单。

![image-20230507002834816](https://raw.githubusercontent.com/Jingqing3948/FigureBed/main/mdImages/8a04dc9794b9fae3b772c1efab38b648.png)

Individuals and interactions over processes and tools 

Working software over comprehensive documentation 

Customer collaboration over contract negotiation 

Responding to change over following a plan

敏捷开发需要程序员之外的项目经理，运营，测试……

敏捷开发重视价值，不要设计没价值的东西。实践一定要解决文分体，文档一定要有对应受众，不要过度设计系统。

敏捷开发注重 steer而不是percise prediction。由customer priorities 和 programmer estimates of  feature difficulty 共同制定不断迭代的计划。

开发原则：

• Emphasis on simple design and refactoring.

• Removing duplication.

• Increasing cohesion. 

• Reducing coupling

常见的敏捷开发方法：如极限开发 Extreme Programming.

- 每天构建几次新版本
- 每两周给用户提交一版增量
- 需求由用户描述
- 程序员Pair programming（两个人坐一起一起开发，效率差不多，而且对彼此代码更加了解）
- 先开发测试单元，再写代码
- 测试过了才算构建完成

Test Driven Development (TDD): 

- Define both an interface and a specification. 
- 先写test后写code。
- 基于增量设计测试 Incremental。
- 每次构建后自动化测试 Automated。
- 客户参与测试 User involvement，测试包括程序员单元测试（unit test），客户确认测试（acceptance test，看是否符合需求）。

敏捷开发的问题：

- 客户易失去积极性 customers interests.
- 团队成员未必喜欢团队开发。

- 多方利益相关，prioritising change is difficult.
- 难维持简洁性，maintaining simplicity.
- 频繁变化，合同设计困难 contract。

适用于多变的，客户要求明确的中小型系统。

## requirements

确定需求是软工设计中最重要的部分。

- feature to satisfy customer.

- indicates what should this sys do.
- 可能是高层抽象的需求 high-level abstract 或者底层具体的 low-level specific.

Stakeholder 利益相关者：受系统影响的组织或个人（当然有的软件可能是针对市场需求开发，而不存在具体的用户）。这些人站在不同角度上有不同见解。

客户不一定清晰描述其需求，也不一定清楚产品特性和功能，且其需求可能不断变化。

在确定需求环节投入的额外时间长远角度来看会节省更多的时间和金钱。

需求分为：functional 和 non functional.

### functional requirements

定义系统的需求，要干什么。比如教务系统对于老师和学生端提供的不同的服务。

这一部分要完整 completeness 清晰一致 consistency 的描述大需求，避免不必要的误解。

### non-functional requirements

这一部分比功能性更重要，相当于不满足这一部分系统错误，不满足功能需求系统有一些小bug。

Define system properties and constraints，比如时空复杂度，设备 capability。

Process requirements：比如质量标准，编程语言等。

Organisational requirements: 如系统要符合IT政策规定。

external requirements: 比如“用户密码不能泄露”。

![1685634113657](https://raw.githubusercontent.com/Jingqing3948/FigureBed/main/mdImages/1685634113657.png)

非功能性需求需要定量描述指标。不然比如“希望程序跑的快一点”这就很模糊。要有measure的方法区测量quantitative定量指标。

### Requirement conflicts

要trade-off权衡需求，让所有人都同意一个最优需求。

### Requirement document

Software Requirements Specification (SRS) 软件需求规范，确认测试的参考规范，指明了应该实现的需求，但是不指明如何实现。

### Requirements Capture

Background Reading

Interviewing

Observation（观察用户使用系统的情况）

Document or Record Sampling（专业的observation）

Questionnaires

## 敏捷开发中的需求

### usr stories

用户需求被称作用户故事，一两句话写在卡片上。

customer 给他们排序需求，development team分解实现任务。

*As a user,  I want to backup my entire hard drive  so that I won’t lose any work.*

写在 stories cards 上，按顺序贴在墙上大家讨论，注意重点不是记录而是大家的讨论。

### Project glossary

一些项目相关的专业术语，建议总结出来方便大家理解讨论。

![image-20230601235612956](https://raw.githubusercontent.com/Jingqing3948/FigureBed/main/mdImages/image-20230601235612956.png)

### Epics

大的 usr story。通常开始讨论前被拆分为小的块。

![image-20230601235720919](https://raw.githubusercontent.com/Jingqing3948/FigureBed/main/mdImages/image-20230601235720919.png)

### Acceptance Criteria

验收标准，通常写在故事卡背面，有助于理解需求和 invite negotiation with the team about the business  value that we are trying to create.

### Non-functional Requirements as User Stories

比如用户表示：我希望电脑打cf fps高于100.

### usr stories注意事项

1. 谁都能写，最好让更多的成员写。
2. 整个 agile development 过程中都可以写。一开始开故事讨论会确定基本，后续随时可以添加。

### Product backlog

需求按优先级排列的需求表。综合考虑多方因素。

MoSCoW：一种 dsdm 动态系统开发方法。

- must have：最重要的。
- should have：如果时间资源超限可以被取代。
- could have：用户期望的需求，完成后用户满意度会高。但是不必要。
- want to have: 当前阶段不重要的。

### Estimating

估计项目用时。

>  story point：故事点，用于表示完成一个产品待办项或者其他任何某项工作所需的所有工作量的估算结果。
>
> 当采用故事点估算时，我们为每个待办项分配一个点数。待办项估算结果的原生数据并不重要，我们只关注最后得到的相对估算结果。一个估算值为2的用户故事应该是估算值为1的用户故事的2倍。而它也应该是另一个估算值为3的用户故事的三分之二。
>
> 团队不要采用100、200、300，或者1百万、2百万、3百万，而要使用1、2、3。估算结果是比值，而不是绝对值。
>
> [敏捷开发中到底什么是故事点（Story Point）？ - 知乎 (zhihu.com)](https://zhuanlan.zhihu.com/p/351172855)

### 评判 good usr story

INVEST原则。

– Independent – Negotiable – Valuable – Estimatable – Small – Testable

### Prototyping

physical：比如画gui。

logical：元素，元素之间的关联……

Low-fidelity 低保真：最简单，比如手绘图，纸板做的，快速验证产品概念的可行性。

Medium-fidelity 中保真：数字模型。

high-fidelity：如3d打印，最接近产品但是制作麻烦。

![1685636483862](https://raw.githubusercontent.com/Jingqing3948/FigureBed/main/mdImages/1685636483862.png)

## analysis

仅仅从用户的需求来看得到的消息不全面，还需要分析。用户可能认为你明白了，或者他考虑不全面，觉得一些地方是不需要的。

因此我们需要分析来 Refining requirements。

gather requirements-analyse in real world context-develop the architecture

分析包括：Textual analysis (针对文档分析)，Entities and concepts（应用层面分析），Experience（过往经验分析）

### conceptual model

面向对象的UML图。

> 边界类用于系统外部环境与内部交互进行建模的类。我的理解是不同系统之间的胶合层。能够减少系统之间的耦合。
>
>  控制类用于对一个或几个用例所特有的控制行为进行建模。控制类源于对用例场景中行为的定义。
>
>  实体类是对必须存储的信息和相关行为建模的类。
>
> [UML-分析类_Iron_Sky的博客-CSDN博客](https://blog.csdn.net/iron_sky/article/details/45478455)

![image-20230602013913446](https://raw.githubusercontent.com/Jingqing3948/FigureBed/main/mdImages/image-20230602013913446.png)

attributes: 属性和相应数据类型。比如姓名，字符串。

Operations：行为方法。一个operation一般只做一件事。

relationships: – Association – Inheritance.

- associations 指两个类之间存在双向联系。比如一个老师教多个学生。有1对1,1对多等关系。
- Inheritance 继承，父类泛化子类特化。

Activities:  

1. Identify Entity, Boundary and Control classes 
2. Identify class relationships 
3. A conceptual class diagram 
4. Identify attributes for each entity class 
5. Add constraints 

## design

design 是把分析模型转换成设计模型，不是代码实现！implementation才是实现。

design must have a purpose: how things works.

A software design: enough information for a  development team to implement the solution.

### roles

- finish non-functional requirements
- break down the overall task.
- Create a ‘skeleton’ of the system 创建易于实现的骨架结构。

### Fundamental Concepts

- Abstraction：抽象类和行为的功能。
- Encapsulation：information hiding。限制某些对象对内容的直接访问。
- Modularity：封装成模块，提供接口给其他模块。
- Coupling：耦合，模块间关系紧密程度。最好是loose 松耦合，这样不容易牵一发而动全身。
- Cohesion：内聚，模块内部自己元素的相关度。最好是high的。
- Refactoring：在代码正常完成要求的前提下修正代码减少重复。主要改进非功能属性。

面向对象设计的好处：对象就是实体；对象可以重用，继承；有的系统对象是现实世界的明显映射。

### steps

conceptual class diagram

Class Relationships

operations

Describing methods

Captures implementation requirements

Produce detailed design class diagram

## Implementation

分析和设计阶段基本上把创意都列出来了。实现就是比较机械地按照前面的设计去敲代码。

利用一些组件去实现。组件主要包括：excutable 可执行文件，file 源码和数据，document，table 数据库表。

implementing subsystem 实现部分功能，利用打包功能导出一个有接口的模块.

Integration Build Plan 迭代构建项目,每次构建指出构建实现的功能和构建需要的子系统、组件。

OOP：有类，对象，方法。但是关联不是双向的，而是只能单向的，比如：

![1685692184183](https://raw.githubusercontent.com/Jingqing3948/FigureBed/main/mdImages/1685692184183.png)

![1685692194908](https://raw.githubusercontent.com/Jingqing3948/FigureBed/main/mdImages/1685692194908.png)

一对多可以在一个类里包含另一个类的一个对象集合。

类的实现要从最小耦合到最大耦合 least  coupled to most coupled。

## Testing

在交付给用户前尽可能发现错误，验证每个阶段的结果。测试占据了40%。

组件层面：开发者测试。

集成测试：测试工程师，专注于质量。

- Validation testing：验证测试，测试系统正常需求已经满足。
- Defect testing：检测系统的缺陷。

### Testing policies

我们不可能把所有可能情况都找到并且测试出来。因此只能选取有代表性的子集。

好的测试：测试人员能预料到可能哪里出错；没有多余的测试用例；应选取“最可能出错”的用例；合适的复杂程度。

![1685693050733](https://raw.githubusercontent.com/Jingqing3948/FigureBed/main/mdImages/1685693050733.png)

test case：输入的规范和预期的输出。

test data：输入。

![1685693215844](https://raw.githubusercontent.com/Jingqing3948/FigureBed/main/mdImages/1685693215844.png)

### testing strategy

what 测试用例？when 测试？how to 测试？如何比对输出是否正确？

test cases 示例（正确的输入。错误的输入比如学号输入英文）：

![image-20230602160905111](https://raw.githubusercontent.com/Jingqing3948/FigureBed/main/mdImages/image-20230602160905111.png)

Test Procedures 测试程序，通常设置为可通用的，便于之后修改重用。这个程序不一定是代码，可能以流程指导的形式（比如按下login按钮，输入账号99001122登录……）

test matrix: 

![image-20230602161119974](https://raw.githubusercontent.com/Jingqing3948/FigureBed/main/mdImages/image-20230602161119974.png)

发现缺陷：比如上例，错误的密码也能登录，于是测试工程师把错误信息返回给开发者：

![image-20230602161157432](https://raw.githubusercontent.com/Jingqing3948/FigureBed/main/mdImages/image-20230602161157432.png)

### Testing: Techniques

黑盒测试，black box/behavior test，即我们要测试的模块，对我们来说像一个内部结构不可见的黑盒子，我们重点关注他行为对不对，与外界的接口是否正确，访问外界数据库正不正确。

- Partition testing：典型的黑盒测试，把数据分成等效的几个区域，比如正数负数0.
- Scenario-based testing：从用户角度触发，分析用户可能的正确和错误操作。
- Regression Testing：集成测试，随着添加增量也不断添加新测试，每次运行所有测试用例，确保系统更新的时候以前的功能没有受干扰。

白盒测试，white/glass/clear box test，主要关注程序内部结构按规范运行，所有内部组件都正确。

确保盒内的所有路径都被正确执行过；考虑正确和错误用例；在边界内外测试；尽量使用内部数据结构。

- Basis Path Testing：执行所有路径的最少用例数。

![1685696256126](https://raw.githubusercontent.com/Jingqing3948/FigureBed/main/mdImages/1685696256126.png)

总体测试流程：白盒测试，建立 test harness 测试装置，测试正确性，测试健壮性；然后黑盒测试。

### TDD

```java
assertEquals(20, student.getAge());//判断返回值是不是20岁
```

在开发代码前编写测试。 simple, short-cycled mechanism。

![1685710646767](https://raw.githubusercontent.com/Jingqing3948/FigureBed/main/mdImages/1685710646767.png)

small cycle：编写测试，编写代码，测试失败，修改代码，测试通过。

# Software architecture

功能需求和软件架构关系紧密，非功能需求是软件架构的选择结果（好的架构运行效率高之类的）。可以以表格或图的形式，比如UML图。

设计难以更改。敏捷开发的早期阶段就是设计系统架构。

好处：

- system analysis：利于分析系统是否满足非功能需求。
- large-scale reuse：架构可重用，更安全、更快。
- Stakeholder communication：可以 成为一个讨论点。

# Project management

让项目能在有限的时间和预算范围内按预期保质落地。

软件工程比较灵活，没有标准的完全正确的方案，需要随机应变，敏锐的洞察力。

## Project planning

最耗时的环节。

计划只有在项目完成时才算完成，因为计划在项目开发阶段也不断变化。

计划可能包括多种，如质量计划，员工开发计划……

## Activity organisation

活动应该组织成切实的输出以及可以判断的进度。

milestone：标志阶段的结束，不一定是能输出给用户的成果。比如文档，或者逻辑阶段的结束。

Deliverables：可以交付给用户的设计，规格等成果。

![image-20230602221047262](https://raw.githubusercontent.com/Jingqing3948/FigureBed/main/mdImages/image-20230602221047262.png)

## Project scheduling

估计项目时间预算的花费，并按顺序排布。

比较难估计。通常根据过往项目经验估计。

先把大项目分解为几个小项目，然后并发的组织任务——尽可能充分利用劳动力 workforce，以及 Minimise task dependencies 尽量减少因为前面的任务没完成对后面任务的延误。

要对错误有预期，没错误是不可能的。约30%预料到的问题+20%未预料到的问题。

通常用图表表示：

任务表：

![1685724379869](https://raw.githubusercontent.com/Jingqing3948/FigureBed/main/mdImages/1685724379869.png)

活动网：最长的是关键路径，因为这条路径任务要是延期了，整个工期都要延期。

![1685724395138](https://raw.githubusercontent.com/Jingqing3948/FigureBed/main/mdImages/1685724395138.png)

甘特图：

![1685724424966](https://raw.githubusercontent.com/Jingqing3948/FigureBed/main/mdImages/1685724424966.png)

Monitoring/Reporting：定期周会周报监督进度。

### Metrics

代码行数，代码中的缺陷，测试用例通过情况。

### People in the process

选择成员，管理，激励他们，解决大家遇到的技术和非技术问题。

- Consistency：对大家态度一致。
- Respect：大家拥有技能不同，互相尊重。
- Inclusion：让大家都参与进项目，聆听所有人的意见。
- Honesty：进度保持诚实。

### Group working

不仅仅是个人的集合。团队能力的组成，凝聚力，沟通，大型团队的层次结构。

敏捷开发的团队合作方法：Scrum approcah，有一个Scrum master，日会，跟随进度，记录决策，和客户等沟通。

项目被分解为易于管理和理解的小部分。不稳定的需求不影响项目进度。团队成员对项目把控更到位 visibility，客户能常常看到项目进度，双方交流更相信彼此。

## Risk management

失败是常有的事。我们要做好备选方案，推迟时间或者放弃目标。

风险管理是预测可能的失败，决定该在什么地方制定备选方案 alternative plans 。

Project risks：项目开发或者资源的问题。比如项目员工走了；原材料没法及时收到了；项目要求时间提前了。

Product risks：产品质量和性能的问题。比如开发软件出bug了，开发出的软件质量不行，用户对gui不满意，有没考虑到的错误。

Business risks：开发，采购组织的问题。比如上市了发现其他公司的技术，竞品比我们的好；负责该系统的组织因财务原因倒闭了。

Project+Product：需求一直变；分析阶段太慢了；开发团队技能能力不行；项目比预想的要大。

### Risk Identification

识别风险。

### Risk Analysis

评估风险的影响。

### Risk Planning

制定应对风险的计划。

### Risk Monitoring

在项目开发过程中监控风险。

### Avoidance Strategies

尽量使用熟悉的模式，但是也接受新技术，时刻关注市场动向；

对员工合理培训，确保技术没被掌握在一个人手中；创造良好的工作环境；

员工不要欺瞒进度，高管要时刻了解进度以及其对项目的重要性。

### Contingencies 突发事件

换人，换工具，换资源。

改变，舍弃一部分目标。

争取做的改动最小，但是损失也降到最小。

我们知道敏捷开发持续提交小版本，因此经常进行测试修改当阶段错误。但是缺陷在于敏捷开发不注重长远眼光 long term planning，因此可能带来一些问题。

## Quality Management

对质量的评估，最好从软件开发过程中抽离出来。因为开发的过程会错误影响开发者自己对质量的评估（我都这么努力了，做出来的东西肯定很好吧）。

瀑布模型中在系统实现后单独测试软件质量。敏捷开发在每次发布新版本的时候测试。因为主要是在实际使用场合的测试，因此会比“为了达成测试而开发”的系统想的更多一些。

好的软件符合目的 Fitness for purpose，标准好，易于使用，高效，代码写的好（当然从用户角度来说他看不到这一层。但是代码写得好是前提，比如时间复杂度运行得快）。开发者需要考虑用户的需求前提下，自己用专业知识思维去想：这个需求实现合理吗。然后多喝用户沟通表达看法。

区分bug和特性：正常运行的是特性。有的时候哪怕系统除了意想不到的bug，但是功能意外的很合适，这也可以是特性。

特性很难移除，当用户开始依赖特性功能的时候，移除用户可能不乐意不习惯；而且向后（版本）兼容变得很难。

软件标准有很多好处，新员工快速入手，大家更熟悉项目等，但是标准可能对一些过去常常发生而现在不怎么出现的问题采取忽略的态度，从而兼容性上出现问题；而且可能浪费很多时间填文书。注重标准好的部分。

## Design principle

设计的软件不仅要正确，高效运行，还要在限定时间，人力，软件，经济条件下。差代码后期可能要花很多时间弥补隐患；而且不同模块的代码经常交互，差代码还会有安全隐患。

我们仅仅想着怎么“正确”地编写代码是不够的，还要更省事省力地去开发。

Software lifespan：软件生命周期，软件是一直在持续开发的，比如新技术，客户新需求。

Software scale：项目大多数规格很大，很多人开发，代码多，多次修订。

Decomposition：项目分解为小模块开发。有Locality（实现一个模块可以不用检查其他模块的实现）和Modifiability（修改一个模块也不用考虑用使用这个模块的模块）的特点。

Specification and implementation：规范连接了设计和实现，规定了模块应该提供哪些服务以及如何使用服务。模块之间的交互应该仅仅限于规范，以此实现分解后模块的locality和modifiability。

Classes and Methods：面向对象思想中的模块为类。

Separation：分开考虑一个模块要实现的功能what和如何实现how。一个应用程序级别，一个实现级别。

Splitting Methods and Classes：设计方法和类的时候好好考虑如何拆分方法和类。类太大了考虑一下要不要拆，几个变量模式比较常用考虑一下要不要抽象成类。

Helper Methods and Classes：有一些方法和类只是为了抽象出来辅助拆分代码用的，这种一般是私有的helper method和嵌套的nested class，只在特定类里起作用，对其他类是private的。

Reuse：用以前的代码重复实现功能，节约代码，而且一改全改。重用代码可能厂商提供，编程语言提供，早期开发者开发。

Abstraction and generalisation：抽象是提取一些概念，比如接口，类；泛化是提取一些重复元素，以便代码重用，比如父类。

UML class diagrams：比代码更高的层次，是面向对象编程很重要的内容。

Static v. Dynamic：匹配静态内容和代码运行时产生的内容。对应UML中的类图 class diagram 和顺序图 sequence diagram。

图源：[UML类图与顺序图_顺序图和类图的关系_lingchen336的博客-CSDN博客](https://blog.csdn.net/lingchen336/article/details/111400141#:~:text=UML的顺序图是用来表示启动程序时，会按照怎样的顺序执行那些方法、那些现象会依什么顺序发生。,类图表示“不因时间变化的部分（静态关系），二顺序图则表示”随时间变化的部分（动态行为）“。)

![img](https://raw.githubusercontent.com/Jingqing3948/FigureBed/main/mdImages/2020121909185527.png)

![img](https://raw.githubusercontent.com/Jingqing3948/FigureBed/main/mdImages/20201219091916200.png)

class and objects：类和对象。对象是类的实例 instance。两者关系很像static 中的method和动态中的method call。

static：静态方法只能被类自己和其附加类调用。静态类实例化的对象是只有一个通用的对象，而不是实例化出无数个对象。

Variables and referencing：java中变量是对对象的引用，比如var2=var1不是赋值而是改变var2指向var1的引用。包括一个变量的自带方法的

![image-20230603233317997](https://raw.githubusercontent.com/Jingqing3948/FigureBed/main/mdImages/image-20230603233317997.png)

![1685806438306](https://raw.githubusercontent.com/Jingqing3948/FigureBed/main/mdImages/1685806438306.png)

Scope and garbage collection：对象的作用域只要有变量还在引用这个对象，他就活着。没有人引用的时候就自动垃圾回收。

基本设计原则：设计对象指代目标；对象方法指代其能做的操作；Locality和Modifiability；对象只能通过方法调用交互。

 Client-Contractor model：一种思想，一个对象调用其他对象的方法得到返回值来使用它，像客户和承包商一样。现代社会大进步的原因就是大家各专其职，因此项目中各个类各专其职也能在同样的量的前提下发挥更多的作用。

- 客户给承包商的合同就类似定义类和方法的规范specification。代码编写者有义务Obligations拿了钱benefits，就确保软件正确执行，不做破坏的事等。

- Design by Contract：一种思想。客户端代码应当满足承包商的先决需求pre-conditions；应当对异常做处理（exception）；后置条件post-conditions在测试中作为断言assertions合并到程序中。（前置条件：前提；后置条件：方法运行后的状态）

### **Single Responsibility Principle (SRP)** 

单一责任原则：每个类有一个职责 responsibility，其所有对象都服务于这个职责。Leads to highly cohesive 内聚性高

### Open-Closed Principle (OCP)

开放闭合原则：模块（类和方法）open to extension，close to modification 

对扩展开放，意味着有新的需求或变化时，可以对现有代码进行扩展，以适应新的情况。

对修改封闭，意味着类一旦设计完成，就可以独立完成其工作，而不要对类进行任何修改。比如一个类以不同对象形式表现这就是扩展，因为需求改变或者有新需求。

### **Do not Repeat Yourself principle (DRY)** 

不要复读机原则（不要重复自己原则）。本模块中如果发现重复内容，建议再抽象为方法和类。

### **Liskov Substitution Principle (LSP) **

Liskov替代原理：重写方法，不应该抱着”修改其方法“的想法。不能加强 strengthen 前置条件，不能削弱 weaken 后置条件。

比如父类people方法AddPeople(String name, int age) 子类Student构造方法AddPeople(String name, int age)里先校验一下学生年龄，如果年龄大于25岁则不允许add，这就修改了原来方法。这就是前置条件加强了，要求年龄的限制多了。

后置条件比如原来返回值大于0，现在返回值可以小于0了，那么返回值限制弱了，后置条件削弱了，不满足里氏原则。

参考：[设计模式六大原则(二)----里式替换原则 - 盛开的太阳 - 博客园 (cnblogs.com)](https://www.cnblogs.com/ITPower/p/14826144.html)

Association, Aggregation and Composition：association是两个不同东西的对应，比如家长和孩子。另外两个是子集，aggregation是彼此可以独立存在，比如班级和学生。composition是可以彼此独立存在，比如house和room。

![1685957816284](https://raw.githubusercontent.com/Jingqing3948/FigureBed/main/mdImages/1685957816284.png)

有的时候继承满足不了LSP（比如令正方形为矩形的子类），可以使用聚类来防止破坏LSP。

### **Interface-Segregation Principle (ISP)** 

接口隔离原则：首先接口应该尽量分解为小接口。客户端每个类不应该依赖他不使用的方法。就是接口用啥实现啥，别多实现。

### Dependency-Inversion Principle (DIP)

依赖倒置原则：

> 如果高层模块直接调用低层模块提供的服务，那么就是具体耦合关系，这样高层模块依赖于低层模块就不可避免。但是，如果我们使用抽象耦合关系，在高层模块和低层模块之间定义一个抽象接口，高层模块调用抽象接口定义的方法，低层模块实现该接口。这样，就消除了高层模块和低层模块之间的直接依赖关系。现在，高层模块就不依赖于低层模块了，二者都依赖于抽象。同时也实现了“抽象不应该依赖于细节，细节应该依赖于抽象”。
>
> [面向对象基础设计原则：4.依赖倒转原则 - 知乎 (zhihu.com)](https://zhuanlan.zhihu.com/p/158391778)
>
> ![img](https://raw.githubusercontent.com/Jingqing3948/FigureBed/main/mdImages/171757199427415.png)
>
> [依赖倒置原则（Dependency Inversion Principle） - sangmado - 博客园 (cnblogs.com)](https://www.cnblogs.com/gaochundong/p/dependency_inversion_principle.html)

## Design Patterns

### Decorator Design pattern

用于扩展系统功能的装饰模式。

比如我们有一个鸭子类，可以调用其鸭子叫的方法。我们初始化了一个鸭子对象，如何统计这个对象叫了几次，也就是其鸭子叫方法被调用了几次？记住OCP原则不允许我们直接修改鸭子类。

我们可以用一个计数类来把鸭子类包住。

<img src="https://raw.githubusercontent.com/Jingqing3948/FigureBed/main/mdImages/image-20230605183433552.png" alt="image-20230605183433552" style="zoom:67%;" />

装饰模式实现一个接口，接口中的方法通过该变量的同一个参数去调用同一个方法，比如本例中是鸭子呱呱的计数，每次调用++。

### Adapter design patterns

<img src="https://raw.githubusercontent.com/Jingqing3948/FigureBed/main/mdImages/1685983233527.png" alt="1685983233527" style="zoom:50%;" />

![1685983255642](https://raw.githubusercontent.com/Jingqing3948/FigureBed/main/mdImages/1685983255642.png)

这个例子包括了几个原则：

1. 首先不同接口功能尽量分开，ISP，也就是说鸭子叫和计数不要写一个方法里。
2. 不能直接修改类，OCP。
3. 如果想鸭子叫同时计数，直接调用包装类即可，DRY。
4. GooseAdapter 只访问了 Goose 的 honk 方法而不是对其完全访问，DIP。这里如果改成继承的子类，就有全部访问权了。

### Wrapper design patterns

Wrapper 模式包括 decorator 和 adapter。decorator 主要是外面的包裹类和里面的类都实现一个接口，比如上例的鸭子嘎嘎。adapter 是内部类不需实现。

Immutable View：前面提过java引用的问题，比如两个变量引用同一个对象，一改都改。这样就相当于有引用的时候，该目标对象完全暴露出来了。在wrapper里我们可以限制包装类：禁止修改对象，修改则抛出异常。

![1685986070602](https://raw.githubusercontent.com/Jingqing3948/FigureBed/main/mdImages/1685986070602.png)

### Composite Design Pattern

包装了一个集合。

![1685985869548](https://raw.githubusercontent.com/Jingqing3948/FigureBed/main/mdImages/1685985869548.png)

### Observer Pattern

比如看到绿灯汽车知道该走了，这种观察者一个变量改变影响其他变量的实现。

java是提供了observable的api的，当对应变量值改变时触发函数。

![1685986641156](https://raw.githubusercontent.com/Jingqing3948/FigureBed/main/mdImages/1685986641156.png)

![1685986768769](https://raw.githubusercontent.com/Jingqing3948/FigureBed/main/mdImages/1685986768769.png)

![1685986796502](https://raw.githubusercontent.com/Jingqing3948/FigureBed/main/mdImages/1685986796502.png)

不过这种写法违反了OCP，因为我们修改了dogbot让他主动实现observer的update。我们可以采用decorator design 方法。

![1685989787205](https://raw.githubusercontent.com/Jingqing3948/FigureBed/main/mdImages/1685989787205.png)

如果dogbots是一个接口，dogwatcher 可以实现计数功能，还可以在文件，在gui中实现dogbots，实现集合的dogbots，两者代码几乎是分离的。

我们先用一个ObservableDogBot类继承Observable，且实现未完成的dogbots，然后写一个dogreporter实现observer。最后`rover.addObserver(reporter1);`

### Factory Methods

如果我们设定饥饿值大于6的狗要被构造为greedyDogBot是普通狗的子类，那么PlainDogBot和greedyDogBot都直接提供出来构造方法不太好。可以通过一个构造类来判断需要返回什么对象。

![image-20230606123312445](https://raw.githubusercontent.com/Jingqing3948/FigureBed/main/mdImages/image-20230606123312445.png)

这个构造方法可以返回接口类，但是实际方法里返回的都是实现了接口类的具体类，这样可以隐藏返回类类型：

![1686026157643](https://raw.githubusercontent.com/Jingqing3948/FigureBed/main/mdImages/1686026157643.png)

工厂类甚至自己可以包装好类然后送回来一个包装类：

![1686026296136](https://raw.githubusercontent.com/Jingqing3948/FigureBed/main/mdImages/1686026296136.png)

当然工厂方法除了静态的，也可以是非静态的工厂对象。我们可以利用不同的工厂对象包装不同的包装类，这样还能进一步隐藏返回对象的一些信息。

比如下一个例子中，spy狗多一个watcher。我们用多态方法初始化两个 Dog Factory，但是一个是实际SpyDogFactory类型的，一个实际是PlainDogFactory类型的，因此两者调用makeDogBot的时候会返回不同的狗。

![1686026555327](https://raw.githubusercontent.com/Jingqing3948/FigureBed/main/mdImages/1686026555327.png)

像计数等额外包装功能，我们也可以不用“先用一个计数类包装普通类，再用工厂类包装计数类”，而是把计数内容包装在工厂类里。

![1686026955303](https://raw.githubusercontent.com/Jingqing3948/FigureBed/main/mdImages/1686026955303.png)

![1686026838784](https://raw.githubusercontent.com/Jingqing3948/FigureBed/main/mdImages/1686026838784.png)

### Singleton Design Pattern

构造方法不一定返回必须是新创建的对象，工厂类里可以返回已有的对象。

![1686027588395](https://raw.githubusercontent.com/Jingqing3948/FigureBed/main/mdImages/1686027588395.png)

注意LSP原则。

### Object Pool Design Pattern

对象池模式，保留一个对象列表，在需要的时候返回一个特定对象。

### Strategy Design Pattern

选择要执行的策略。

![1686027938500](https://raw.githubusercontent.com/Jingqing3948/FigureBed/main/mdImages/1686027938500.png)

上图是狗执行几次action的函数。我们也可以利用重写的Comparator实现条件结构，比如比较ab两狗叫了几次，叫的少的补差。

### State Design Pattern

可以改变对象的状态类型。

下面改变的几个类型都是accountState抽象类的实现类，通过多态的方式初始化。

![image-20230606180142747](https://raw.githubusercontent.com/Jingqing3948/FigureBed/main/mdImages/image-20230606180142747.png)

### Bridge Design Pattern

解耦思想。比如我们需要12种颜色的画笔，可以选择12根固定颜色的蜡笔，或者1根毛笔和12种颜料。毛笔的例子就很好地解耦了画笔和颜色。

例子来源：[处理多维度变化——桥接模式（一）_LoveLion的博客-CSDN博客](https://blog.csdn.net/LoveLion/article/details/7464183)

下例：求父子交集。

![image-20230606181219816](https://raw.githubusercontent.com/Jingqing3948/FigureBed/main/mdImages/image-20230606181219816.png)

### Flyweight Pattern

将那些大量的，具有很多内部状态而外部状态很少的对象进行共享，可以以类似缓存的方式共享。

概念来源：[精读《设计模式 - Flyweight 享元模式》 - 知乎 (zhihu.com)](https://zhuanlan.zhihu.com/p/336732854)

![image-20230606181617881](https://raw.githubusercontent.com/Jingqing3948/FigureBed/main/mdImages/image-20230606181617881.png)

# Open Source Software

free of charge, free of legal restrictions on usage. 

也需要敏捷开发，但是开发方式略有不同，毕竟不是利益相关的模式。强调个人之间的密切交互 close personal interaction，开发者也是自己的客户，因此有很多人做测试，而且修改后的小版本可以很快发布；通常分的小模块很多，世界各地的大家分别开发。

世界各地大家通过电子方式互相交流 electronically。

整体协调者 overall co-ordinator 通常由志愿者负责。

商业软件和OSS就像大教堂和集市的区别，大教堂需要有一个整体的建造目标，大家一同努力。集市可能由城管那样的整体协调者管大家秩序，但是大家还是偏personal一点。

如果自己公司自己开发项目，当然需要大量人力财力开发和后期维护；选择闭源软件，就绑定在供应商身上了（垄断技术），他们需要进一步收费咱也得交。开源软件就不用担心支付费用这类问题或者供应商倒闭问题，但是不是是开源软件就能拿来用的，注意版权问题。

开源软件大多数有一小部分人在开发核心core以及新功能，大多数人在correcting  defects。大多数情况下开发者更愿意维护现有fork分支而不是一味开发新分支。

开源软件也有一个control structure，通常由最初提出项目的人拥有软件的最终决议权，由一些商业公司管理而并非个人（这样能多保质一点），比如安卓开源软件由谷歌掌握控制权，对于提交的fork和patch有权最终决定下一个版本更新的内容。

- contributor：OSS中做贡献的人。
- developer：在软件平台上开发应用的人。
- verifier：测试 change request 是否正确的人。
- approver：决定这些修改是否要合并进大版本的人，和verifier都需要审核面试筛选。
- Project leads：监督单个项目的工程。

## software freedom

- 运行程序的自由 run the program

- 学习程序运行原理和按自己意愿修改代码的自由 study how this program works, change it so it does your compute as you wish （当然前提是能访问到源码）

- 分发软件副本的自由 redistribute copies
- 发布自己的版本给他人的自由  distribute copies of your modified versions to others。

## Copyright

只有制作者producer有权利制作副本和创建新内容 produce copies and create new work based on it，但是可以授权允许别人复制和改编该作品 make copies of the work and adapt it。制作者可以通过收费等方式赋予这些权力给他人，或者对改编的范围加限制，因为这算是加在producer身上的一种义务，有点回报也正常。

## Copyleft

但是OSS的版权声明采用的是copyleft，一种 free software license，并不是限定他人复制改编的权力，而是赋予他人这种权力。许可证内容包括：声明源代码可用，以及改编允许的范围。

## voting

有权投票的人每人最多一票；没权投票的人不能投；有权投票且选择投票的人不能被阻止投票；其须拥有充足的选择 full choice；其投票结果必须被正确统计不能被别人篡改；总票数正确相加，不能篡改；大多数时候没有人能知道任何一个投票者的选择。

电子投票有风险，比如数据容易被篡改，被伪造等。

# Software Development Tools

## Software Craftsmanship and Clean Code

注意代码整洁，比如格式、注释等。

## Saying “No”

不要一直盲目答应老板和客户的需求，程序员更熟悉代码，而且需要帮老板规避可能发生的错误。

## Learning from Mistakes

## Microsoft’s Best Practices

### Revision Control System

版本控制。

roll-back：版本回滚。

check-out：开发者拉下来代码。

check-in：开发者提交自己的修订版。

conflict：两个人的提交出冲突了。

merge：合并入主分支。

### Daily Build

每日构建一次代码，编译链接源代码，进行一些测试，确保第二天大家能使用最新版本。

### Continuous Integration

开发人员也建议每天check-in一次。

### Build Verification Tests

断言和单元测试。

### Bug Database

记录以前的bug记录，解决方法，严重程度，优先级等信息。

### War Team and Bug Triage

发布前，作战小组确认系统“好到可以发布”。检查运行是否正常，剩余的bug严重程度等。

### Code reviews and coding guidelines 

团队对彼此代码进行彻底审查。

### Globalisation and Localisation

针对不同语言、脚本的差异处理。

### Documentation Generators

文档生成。
