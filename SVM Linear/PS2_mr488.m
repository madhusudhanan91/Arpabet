% Read the file A.csv and split the first 175 values as Training Set for File A
A1 = csvread('A.csv');
Atrain = A1(1:175,:);
p = size(Atrain,1);
q = size(Atrain,2);

% Read the file B.csv and split the first 275 values as Training Set for File B
B1 = csvread('B.csv');
Btrain = B1(1:275,:);
r = size(Btrain,1);

% Split the remaining values as Test Set for File A
Atest = A1(176:end,:);
s = size(Atest,1);
t = size(Atest,2);

% Split the remaining values as Test Set for File B
Btest = B1(276:end,:);
u = size(Btest,1);

% Initialize the Zero matrix for Training Set
Zpq = zeros(p,q);
Zrq = zeros(r,q);
Zp1 = zeros(p,1);
Zr1 = zeros(r,1);
Zrp = zeros(r,p);
Zpr = zeros(p,r);
Z1r = zeros(1,r);
Z1q = zeros(1,q);

% Initialize the Zero matrix for Test Set
Zst = zeros(s,t);
Zut = zeros(u,t);
Zs1 = zeros(s,1);
Zu1 = zeros(u,1);
Zus = zeros(u,s);
Zsu = zeros(s,u);
Z1u = zeros(1,u);
Z1t = zeros(1,t);

% Initialize the Column Vector matrix for Training Set
ep = ones(p,1);
er = ones(r,1);

% Initialize the Column Vector matrix for Test Set
es = ones(s,1);
eu = ones(u,1);

% Initialize the Identity matrix for Training Set
Ip = eye(p);
Ir = eye(r);

% Initialize the Identity matrix for Test Set
Is = eye(s);
Iu = eye(u);

% Initialize the Lower Bound,Upper Bound so that the solution lies between
% lb <= function <= ub and linear equality constraints Aeq,Beq
lb = [];
ub = [];
Aeq = [];
Beq = [];

% Construct the Training Set Classifier
A = [-Atrain ep -Ip -Zpr;Btrain -er Zrp -Ir;Zpq Zp1 -Ip Zpr;Zrq Zr1 Zrp -Ir];
B = [-ep;-er;Zp1;Zr1];
c = [Z1q 0 (1/p)*transpose(ep) (1/r)*transpose(er)];

% Apply the Linear Programming function for Training Set
X1 = linprog(c,A,B,Aeq,Beq,lb,ub);
X1trans = linprog(transpose(c),A,B,Aeq,Beq,lb,ub);

% Compute the Omega and Gamma values for Training Set
omegatrain = X1trans(1:q);
gammatrain = X1trans(q+1);

% Display the Training Set values
X1
X1trans
omegatrain
gammatrain

% Construct the Test Set Classifier
A2 = [-Atest es -Is -Zsu;Btest -eu Zus -Iu;Zst Zs1 -Is Zsu;Zut Zu1 Zus -Iu];
B2 = [-es;-eu;Zs1;Zu1];
c2 = [Z1t 0 (1/s)*transpose(es) (1/u)*transpose(eu)];

% Apply the Linear Programming function for Test Set
X2 = linprog(c2,A2,B2,Aeq,Beq,lb,ub);
X2trans = linprog(transpose(c2),A2,B2,Aeq,Beq,lb,ub);

% Compute the Omega and Gamma values for Test Set
omegatest = X2trans(1:t);
gammatest = X2trans(t+1);

% Display the Test Set values
X2
X2trans
omegatest
gammatest

% Value Extraction using Normalization for Training Set using the formula ((A*omega)-gamma)
resulttrain = A1*omegatrain - gammatrain;
resulttrain

% Accuracy Calculation for Training Set
countpostrain = sum(resulttrain(:) > 0)
countnegtrain = sum(resulttrain(:) < 0)
sumtrain = countpostrain + countnegtrain;
accuracy = 100 * (countpostrain/sumtrain)

% Value Extraction using Normalization for Test Set using the formula ((B*omega)-gamma)
resulttest = B1*omegatest - gammatest;
resulttest

% Accuracy Calculation for Test Set
countpostest = sum(resulttest(:) > 0)
countnegtest = sum(resulttest(:) < 0)
sumtest = countpostest + countnegtest;
accuracy = 100 * (countnegtest/sumtest)

% Plot the values
plot(resulttrain,'+r'), hold on;
plot(resulttest,'ob'), hold on;
ylim ([-400 3500])